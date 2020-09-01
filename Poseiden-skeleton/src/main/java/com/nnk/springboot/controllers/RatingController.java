package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * Controller permettant de visualiser, d'ajouter, modifier et supprimer des elements a Rating.
 *
 */

@Controller
public class RatingController {

    private static final Logger logger = LogManager.getLogger(RatingController.class);

    private final RatingRepository ratingRepository;

    public RatingController(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }


    /**
     *
     * @param model : parametre a a transmettre au Modele pour exposition a la vue : Liste de rating.
     * @return : Retour de la page html.
     */

    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        model.addAttribute("ratings", ratingRepository.findAll());
        return "rating/list";
    }

    /**
     *
     * @param rating : parametre a a transmettre au Modele pour exposition a la vue add de rating.
     * @return : retour de la pgae html
     */

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    /**
     *
     * @param rating : instace de l'Objet rating pour validation des contraintes de format
     * @param result : resultat de la validation
     * @param model : parametre a transmettre au Modele pour exposition a la vue : Liste de rating.
     * @return : Retour de la page html.
     */

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {

        // Verifie que les datas dont valides et sinon return sur la page d'ajout de données
        if (!result.hasErrors()) {

            // Ajout des elements en BDD et redirection vers Bid List
            ratingRepository.save(rating);
            logger.info("Element Rating ajouté a la BDD");
            model.addAttribute("ratings", ratingRepository.findAll());
            return "redirect:/rating/list";
        }
        logger.info("Format non Valide");
        return "rating/add";
    }

    /**
     *
     * @param id : attribut du Modele rating
     * @param model : parametre a transmettre au Modele pour exposition a la vue : Liste de rating.
     * @return : retour de la page html
     */

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        Rating rating = ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
        model.addAttribute("rating", rating);

        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {

        if (result.hasErrors()) {
            logger.info("Format non Valide");
            return "rating/update";
        }
        rating.setId(id);
        ratingRepository.save(rating);
        logger.info("Element Rating mis a jour en BDD");
        model.addAttribute("rating", ratingRepository.findAll());

        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {

        Rating rating = ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
        ratingRepository.delete(rating);
        logger.info("Element Rating supprimé");

        return "redirect:/rating/list";
    }
}
