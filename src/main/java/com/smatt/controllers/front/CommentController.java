package com.smatt.controllers.front;

import com.smatt.dao.CommentRepository;
import com.smatt.models.Comment;
import com.smatt.service.AuthenticationService;
import com.smatt.service.CommentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by smatt on 21/07/2017.
 */
@Controller
@RequestMapping(value = "/comment")
public class CommentController {

    CommentRepository commentRepository;
    AuthenticationService authService;
    CommentService commentService;
    Logger logger = Logger.getLogger(CommentController.class);

    @Autowired
    public CommentController(CommentRepository c, AuthenticationService aS, CommentService cs) {
        this.commentRepository = c;
        this.authService = aS;
        this.commentService = cs;
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<String> add(Comment comment, @RequestParam(value = "g-recaptcha-response", required = false) String recaptchaResp) {
        logger.info(comment.toString());

        if(!comment.isValid()) {
            return ResponseEntity.badRequest().body("Missing Required Inputs");
        }

        if(!authService.verifyReCaptcha(recaptchaResp)) {
            return ResponseEntity.badRequest().body("reCaptcha Validation Failed! Try Again");
        }

        commentRepository.save(comment);
        return ResponseEntity.ok("Comment Added Successfully");
    }

    @PostMapping("/reply")
    @ResponseBody
    public ResponseEntity<String> reply(Comment comment) {

        logger.info(comment.toString());

        if(!comment.isValid()) {
            return ResponseEntity.badRequest().body("Missing Required Inputs");
        }

        commentRepository.save(comment);

        return ResponseEntity.ok("Comment Added Successfully");

    }


    @GetMapping("/replies/{postId}/{commentId}")
    @ResponseBody
    public ResponseEntity<String> replies(@PathVariable("postId") String postId, @PathVariable("commentId") String commentId) {
        return commentService.replies(postId, commentId);
    }

    @GetMapping("/read/{postId}")
    @ResponseBody
    public ResponseEntity read(@PathVariable("postId") String postId) {
        return commentService.read(postId);
    }


}
