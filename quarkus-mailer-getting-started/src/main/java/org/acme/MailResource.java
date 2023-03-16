package org.acme;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.MailTemplate;
import io.quarkus.mailer.Mailer;
import io.quarkus.mailer.reactive.ReactiveMailer;
import io.quarkus.qute.Location;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.io.File;

@Path("/mail")
public class MailResource {

    @Inject Mailer mailer;

    @Inject
    @Location("hello")
    MailTemplate hello;

    @GET
    @Blocking
    public void sendEmail() {
        mailer.send(
                Mail.withText("quarkus@quarkus.io",
                        "Ahoy from Quarkus",
                        "A simple email sent from a Quarkus application."
                )
        );
    }
    @Inject
    ReactiveMailer reactiveMailer;

    @GET
    @Path("/reactive")
    public Uni<Void> sendEmailUsingReactiveMailer() {
        return reactiveMailer.send(
                Mail.withText("yogapermana1235k4@gmail.com",
                        "Ahoy from Quarkus",
                        "A simple email sent from a Quarkus application using the reactive API."
                ) .addAttachment("my-file-1.txt",
                        "tes file".getBytes(), "text/plain"
                        ).addAttachment("my-file-2.txt",
                        new File("my-file.txt"), "text/plain")
        );
    }

    @GET
    @Path("/html")
    public void sendingHTML() {
        String body = "<strong>Hello!</strong>" + "\n" +
                "<p>Here is an image for you: <img src=\"cid:my-image@quarkus.io\"/></p>" +
                "<p>Regards</p>";
        mailer.send(Mail.withHtml("yogapermana1235k4@gmail.com", "An email in HTML", body)
                .addInlineAttachment("quarkus-logo.png",
                        new File("quarkus-logo.png"),
                        "image/png", "<my-image@quarkus.io>"));
    }

    @GET
    @Path("/qute")
    public Uni<Void> send() {
        return hello.to("yogapermana1235k4@gmail.com")
                .subject("Hello from Qute template")
                .data("name","yoga")
                .addInlineAttachment("quarkus-logo.png",
                        new File("quarkus-logo.png"),
                        "image/png", "<my-image@quarkus.io>")
                .send();
    }
}
