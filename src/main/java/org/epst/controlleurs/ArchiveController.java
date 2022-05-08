package org.epst.controlleurs;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.epst.beans.MessageBean;
import org.epst.beans.MessageBeanRepository;
import org.epst.beans.Utilisateur;
import org.epst.models.ModelMessage;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Path("archive")
public class ArchiveController {

    @Inject
    MessageBeanRepository messageBeanRepository;
    ModelMessage modelMessage = new ModelMessage();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String get1llo() {

        // create a JSON string
        return "Allo";
    }

    @Path("/a1/{matricule}/{date}")
    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public List<MessageBean> getArchive1(@PathParam("matricule") String matricule, @PathParam("date") String date) {
        List<MessageBean> l = modelMessage.getArchiveConversation(matricule);
        System.out.println("matricule: "+matricule);
        System.out.println("taille: "+l.size());
        l.forEach((e)->{
            System.out.println("matricule: "+e.getMatriculet());
        });
        Predicate<MessageBean> t = value -> value.getMatriculet().equals(matricule) && value.getDatet().contains(date);
        return l.stream().filter(t).collect(Collectors.toList());
    }


    @Path("/a2/{matricule}/{date}/{heure}")
    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public List<MessageBean> getArchive2(@PathParam("matricule") String matricule, @PathParam("date") String date,
                                   @PathParam("heure") String heure) {
        List<MessageBean> l = modelMessage.getArchiveConversation(matricule);
        Predicate<MessageBean> t = value -> value.getMatriculet().equals(matricule) && value.getDatet().contains(date)
                && value.getHeuret().contains(heure);
        //
        return l.stream().filter(t).collect(Collectors.toList());
    }

}