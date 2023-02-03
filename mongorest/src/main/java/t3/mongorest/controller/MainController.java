package t3.mongorest.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import t3.mongorest.model.Partida;
import t3.mongorest.model.PartidakRepository;
import t3.mongorest.model.UserRepository;
import t3.mongorest.model.Userr;

@RestController
@RequestMapping(path = "/mongoapi")
public class MainController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PartidakRepository partidakRepository;


    // @RequestMapping("/error")
    // public String handleError() {
    //     return "error";
    // }
    // @PostMapping(path = "/addPartida")
    // public @ResponseBody String addPartida(@RequestBody String name, @RequestParam int puntuazioa,
    //         @RequestParam String data) throws ParseException {
    //     Partida partida = new Partida();
    //     partida.setUser(name);
    //     partida.setPuntuazioa(puntuazioa);
    //     DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    //     try {
    //         java.util.Date date = format.parse(data);
    //         Timestamp dataPartida = new java.sql.Timestamp(date.getTime());
    //         partida.setData(dataPartida);
    //     } catch (ParseException e) {
    //         return "Invalid date format, expected format: yyyy-MM-dd";
    //     }
    //     partidakRepository.save(partida);
    //     return "Saved";
    // }
    //
    // Erabiltzailearen lekua
    //
    @GetMapping(path = "/allusers")
    public @ResponseBody Iterable<Userr> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping(path = "/newuser")
    public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam int date) {
        Userr u = new Userr();
        u.setName(name);
        u.setDate(date);
        userRepository.save(u);
        return "saved";
    }

    @DeleteMapping(path = "/delete/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String user) {
        try {
            long quantity = userRepository.delete(user);
            System.out.println("Ezabatutako ume kopurua: " + quantity);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("Errorea " + user + " erabiltzailea ezabatutakoa");
            return ResponseEntity.notFound().build();
        }
    }
}
