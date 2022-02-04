package MadTests.SpringBootFlywayTest.service;

import MadTests.SpringBootFlywayTest.dataclass.Client;
import MadTests.SpringBootFlywayTest.dataclass.Offer;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface DBService {

    /**
     * Use it to add new offer into the base
     * @param offer "id" will be generated, but rest fields, such as "name",
     * "duration" and "price" should be found in income @RequestBody
     */
    void create(Offer offer);

    /**
     * Use it to get info about all offers
     * @return list of all offers, which currently stored in DB
     * or null if there is no any offer
     */
    List<Offer> readAll();

    /**
     * Use it to delete offer with corresponding "id" from DB
     * @param id integer
     * @return boolean representation of success in deleting
     * process, however could be blocked by table restrictions
     * (can't kill offer, while have client's order on it)
     */
    boolean delete(int id);

    /**
     * Use it to add new client to DB
     * @param client client "id" will be generated, but rest fields, such as "name",
     * "phone" and "time" should be found in income @RequestBody
     * @return boolean representation of success in creation
     * (time collisions may prevent creation)
     */
    Integer create(Client client);

    /**
     * Use it to get info about client
     * @param id integer
     * @return the client from DB corresponding to the "id"
     * or null, if there is no such client
     */
    Client read(int id);

    /**
     * magic
     * @return friendship
     */
    Map<Date, Date> allTime();

}
