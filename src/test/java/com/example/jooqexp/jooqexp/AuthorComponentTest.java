package com.example.jooqexp.jooqexp;

import com.example.jooqexp.db.tables.Article;
import com.example.jooqexp.db.tables.Author;
import com.example.jooqexp.db.tables.records.ArticleRecord;
import com.example.jooqexp.db.tables.records.AuthorRecord;
import lombok.Cleanup;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

@SpringBootTest(classes = JooqExpApplication.class)
class AuthorComponentTest {
    private final AuthorComponent authorComponent;

    @Autowired
    public AuthorComponentTest(AuthorComponent authorComponent) {
        this.authorComponent = authorComponent;
    }

    @Test
    @lombok.SneakyThrows
    @DisplayName("JOOQ works correctly")
    void jOOQWorksCorrectlyTest() {
        @Cleanup
        Connection conn = DriverManager.getConnection(authorComponent.getUrl(), authorComponent.getUsername(), authorComponent.getPassword());
        DSLContext context = DSL.using(conn, SQLDialect.POSTGRES);

        AuthorRecord authorRecord = context.newRecord(Author.AUTHOR);
        authorRecord.setId(63);
        authorRecord.setFirstName("Ivan");
        authorRecord.setLastName("Icvanov");
        authorRecord.setAge(50);

        authorRecord.store();

        ArticleRecord article = context.newRecord(Article.ARTICLE);
        article.setId(63);
        article.setTitle("jOOQ examples");
        article.setDescription("A few examples of jOOQ CRUD operations");
        article.setAuthorId(3);

        article.store();



        Result<Record> authors = context.select()
                .from(Author.AUTHOR)
                .fetch();



        authors.forEach(author -> {
            Integer id = author.getValue(Author.AUTHOR.ID);
            String firstName = author.getValue(Author.AUTHOR.FIRST_NAME);
            String lastName = author.getValue(Author.AUTHOR.LAST_NAME);
            Integer age = author.getValue(Author.AUTHOR.AGE);

            System.out.printf("Author %s %s has id: %d and age: %d%n", firstName, lastName, id, age);
        });

        Result<Record> articles = context.select()
                .from(Article.ARTICLE)
                .where(Article.ARTICLE.ID.eq(53))
                .fetch();
        articles.forEach(article2 -> {
            System.out.printf(" Article %s, %s, %d, %d",
                    article2.getValue(Article.ARTICLE.TITLE),
                    article2.getValue(Article.ARTICLE.DESCRIPTION),
                    article2.getValue(Article.ARTICLE.ID),
                    article2.getValue(Article.ARTICLE.AUTHOR_ID));

        });
    }

}