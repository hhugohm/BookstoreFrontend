package boosktore.frontend.rest;

import bookstore.backend.api.BookstoreDAO;
import bookstore.backend.datamodel.Book;
import bookstore.backend.datamodel.enums.Availability;
import bookstore.backend.datamodel.enums.BookFormat;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.validation.constraints.Positive;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

/**
 * REST Web Service
 *
 * @author hhugohm
 */
@Path("books")
@RequestScoped
public class BooksResource {

    @EJB
    private BookstoreDAO bookstoreDAO;

    /**
     *
     * @param id
     * @return
     */
    @RolesAllowed({"ADMIN"})
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("id") int id) {
        Book book = this.bookstoreDAO.getBookById(id);

        if (book == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
            // ResponseBuilder rb = Response.status(Response.Status.NOT_FOUND);
            // return rb.build();
        }
        JsonObject jo = this.buildBookAsJsonObject(book);
        String joStr = jo.toString();

        Response response = Response.ok().entity(joStr).build();

        return response;

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBooks() {
        List<Book> books = this.bookstoreDAO.getAllBooks();
        JsonArrayBuilder jab = Json.createArrayBuilder();
        JsonObject jo = null;
        for (Book book : books) {
            jo = this.buildBookAsJsonObject(book);
            jab.add(jo);
        }

        JsonArray ja = jab.build();
        String jaStr = ja.toString();

        Response response = Response.ok().entity(jaStr).build();

        return response;

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertBook(String json) {
        JsonReader jr = Json.createReader(new StringReader(json));
        JsonObject jo = jr.readObject();

        String title                = jo.getString("title");
        String description          = jo.getString("description");
        String author               = jo.getString("author");
        String publisher            = jo.getString("publisher");
        Date publishedDate          = null;
        try {
            publishedDate           = new SimpleDateFormat("dd/MM/yyyy").parse(jo.getString("publishedDate"));
        } catch (Exception e) {
        }
        String language             = jo.getString("language");
        int numberOfPages           = jo.getInt("numberOfPages");
        BookFormat format           = BookFormat.valueOf(jo.getString("format"));
        double price                = jo.getJsonNumber("price").doubleValue();
        Availability availability   = Availability.valueOf(jo.getString("availability"));
        int stockQuantity           = jo.getInt("stockQuantity");
        
        Book book = new Book();
        book.setTitle(title);
        book.setDescription(description);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setPublishedDate(publishedDate);
        book.setLanguage(language);
        book.setNumberOfPages(numberOfPages);
        book.setFormat(format);
        book.setPrice(price);
        book.setAvailability(availability);
        book.setStockQuantity(stockQuantity);
        
        this.bookstoreDAO.insert(book);
        
        Response response = Response.created(null).build();
        
        return response;
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatetBook(String json) {
        JsonReader jr = Json.createReader(new StringReader(json));
        JsonObject jo = jr.readObject();
        
        
        int id                      = jo.getInt("id");
        String title                = jo.getString("title");
        String description          = jo.getString("description");
        String author               = jo.getString("author");
        String publisher            = jo.getString("publisher");
        Date publishedDate          = null;
        try {
            publishedDate           = new SimpleDateFormat("dd/MM/yyyy").parse(jo.getString("publishedDate"));
        } catch (Exception e) {
        }
        String language             = jo.getString("language");
        int numberOfPages           = jo.getInt("numberOfPages");
        BookFormat format           = BookFormat.valueOf(jo.getString("format"));
        double price                = jo.getJsonNumber("price").doubleValue();
        Availability availability   = Availability.valueOf(jo.getString("availability"));
        int stockQuantity           = jo.getInt("stockQuantity");
        
        Book book = this.bookstoreDAO.getBookById(id);
        book.setTitle(title);
        book.setDescription(description);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setPublishedDate(publishedDate);
        book.setLanguage(language);
        book.setNumberOfPages(numberOfPages);
        book.setFormat(format);
        book.setPrice(price);
        book.setAvailability(availability);
        book.setStockQuantity(stockQuantity);
        
        this.bookstoreDAO.update(book);
        
        Response response = Response.accepted().build();
        
        return response;
    }
    
    @Path("{id}")
    @DELETE
    public Response deleteBook(@Positive@PathParam("id") int id) {
        Book book = this.bookstoreDAO.getBookById(id);
        this.bookstoreDAO.delete(book);
        
        Response response = Response.ok().build();

        return response;

    }

    private JsonObject buildBookAsJsonObject(Book book) {
        JsonObjectBuilder jb = Json.createObjectBuilder();
        jb.add("id", this.getAsJsonValue(book.getId()));
        jb.add("title", this.getAsJsonValue(book.getTitle()));
        jb.add("description", this.getAsJsonValue(book.getDescription()));
        jb.add("author", this.getAsJsonValue(book.getAuthor()));
        jb.add("publisher", this.getAsJsonValue(book.getPublisher()));
        jb.add("publishedDate", this.getAsJsonValue(book.getPublishedDate()));
        jb.add("language", this.getAsJsonValue(book.getLanguage()));
        jb.add("numberOfPages", this.getAsJsonValue(book.getNumberOfPages()));
        jb.add("format", this.getAsJsonValue(book.getFormat()));
        jb.add("price", this.getAsJsonValue(book.getPrice()));
        jb.add("availability", this.getAsJsonValue(book.getAvailability()));

        JsonObject jo = jb.build();

        return jo;

    }

    private String getAsJsonValue(Object value) {
        return (value == null) ? "" : value.toString();

    }

}
