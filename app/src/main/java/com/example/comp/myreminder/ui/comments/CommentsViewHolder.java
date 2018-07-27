package com.example.comp.myreminder.ui.comments;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.comp.myreminder.R;
import com.example.comp.myreminder.model.Comment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by COMP on 25.7.2018..
 */

public class CommentsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_comment_name)
    TextView commentName;

    @BindView(R.id.tv_comment_mail)
    TextView commentMail;

    @BindView(R.id.tv_comment_body)
    TextView commentBody;

    @BindView(R.id.ll_root)
    View root;

    public CommentsViewHolder(View commentView) {
        super(commentView);

        ButterKnife.bind(this, commentView);
    }

    public void setCommentInfo(Comment comment) {
        if (comment != null) {
            commentName.setText(String.format("Name:\n%s", comment.getName()));
            commentMail.setText(String.format("E-mail:\n%s", comment.getEmail()));
            commentBody.setText(String.format("Body: \n%s", comment.getBody()));
        }
    }

    public void setWhiteBackground() {
        root.setBackgroundColor(Color.WHITE);
    }

    public void setGrayBackground() {
        root.setBackgroundColor(Color.LTGRAY);
    }
}
