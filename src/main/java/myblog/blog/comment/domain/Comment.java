package myblog.blog.comment.domain;


import lombok.Builder;
import lombok.Getter;
import myblog.blog.article.domain.Article;
import myblog.blog.base.domain.BasicEntity;
import myblog.blog.member.doamin.Member;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "comment")
@Entity
@Getter
@SequenceGenerator(
        name = "COMMENT_SEQ_GENERATOR",
        sequenceName = "COMMENT_SEQ",
        initialValue = 1, allocationSize = 50)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Comment extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENT_SEQ_GENERATOR")
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    private int tier;

    // 댓글 표시 순서
    private int pOrder;

    // 셀프조인
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parents_id")
    private Comment parents;

    @OneToMany(mappedBy = "parents")
    private List<Comment> child = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String content;

    @Column(columnDefinition = "boolean default false")
    private boolean secret;



    @Builder
    public Comment(Article article, int tier, Comment parents,int pOrder, Member member, String content, boolean secret) {

        this.pOrder = pOrder;
        this.article = article;
        this.tier = tier;
        this.parents = parents;
        this.member = member;
        this.content = content;
        this.secret = secret;
    }

    protected Comment() {

    }
}
