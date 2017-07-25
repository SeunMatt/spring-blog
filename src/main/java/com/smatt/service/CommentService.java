package com.smatt.service;

import com.smatt.addons.AssetDirective;
import com.smatt.dao.CommentRepository;
import com.smatt.models.Comment;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by smatt on 25/07/2017.
 */
@Service
public class CommentService {

    private CommentRepository commentRepository;
    private Configuration cfg;
    private Logger logger = Logger.getLogger(CommentService.class);

    @Autowired
    public CommentService(CommentRepository c, @Qualifier("freeMarkerConfiguration") Configuration cfg) {
        this.commentRepository = c;
        this.cfg = cfg;
    }


    public ResponseEntity read(String postId) {

        List<Comment> comments = commentRepository.findDirectComments(postId);

        logger.info("post id: " + postId);
        logger.info("comments returned: " + comments.size());

        if(comments.size() <= 0)
            return ResponseEntity.ok("No Comments yet! Be the first to Comment");

        try {
            //process mail template
            StringWriter stringWriter = new StringWriter();
            cfg.setSharedVariable("asset", new AssetDirective());
            Template temp = cfg.getTemplate("partials/comment.ftl");
            Map<String, Object> map = new HashMap<>();
            map.put("comments", comments);
            temp.process(map, stringWriter);

            return ResponseEntity.ok(stringWriter.getBuffer().toString());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Cannot Get Comment Now. Try again later");
        }

    }

}
