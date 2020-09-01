package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
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
 * Controller permettant de visualiser, d'ajouter, modifier et supprimer des elements a CurvePoint.
 *
 */

@Controller
public class CurveController {

    private static final Logger logger = LogManager.getLogger(CurveController.class);

    private final CurvePointRepository curvePointRepository;

    public CurveController(CurvePointRepository curvePointRepository) {
        this.curvePointRepository = curvePointRepository;
    }

    /**
     *
     * @param model : parametre a a transmettre au Modele pour exposition a la vue : Liste de curvepoint.
     * @return : Retour de la page html.
     */

    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        model.addAttribute("curves", curvePointRepository.findAll());
        return "curvePoint/list";
    }

    /**
     *
     * @param bid : parametre a a transmettre au Modele pour exposition a la vue add de curvepoint.
     * @return : retour de la pgae html
     */

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid) {
        return "curvePoint/add";
    }

    /**
     *
     * @param curvePoint : instace de l'Objet Curvepoint pour validation des contraintes de format
     * @param result : resultat de la validation
     * @param model : parametre a transmettre au Modele pour exposition a la vue : Liste de curvepoint.
     * @return : Retour de la page html.
     */

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {

        // Verifie que les datas dont valides et sinon return sur la page d'ajout de données
        if (!result.hasErrors()) {

            // Ajout des elements en BDD et redirection vers Bid List
            curvePointRepository.save(curvePoint);
            logger.info("Element Curve ajouté a la BDD");
            model.addAttribute("curves", curvePointRepository.findAll());
            return "redirect:/curvePoint/list";
        }
        logger.info("Format non Valide");
        return "curvePoint/add";
    }

    /**
     *
     * @param id : attribut du Modele curvepoint
     * @param model : parametre a transmettre au Modele pour exposition a la vue : Liste de curvepoint.
     * @return : retour de la page html
     */

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        CurvePoint curvePoint = curvePointRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid curve point Id:" + id));
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {

        if (result.hasErrors()) {
            logger.info("Format non Valide");
            return "curvePoint/update";
        }
        curvePoint.setId(id);
        curvePointRepository.save(curvePoint);
        logger.info("Element Curve mis a jour en BDD");
        model.addAttribute("curves", curvePointRepository.findAll());
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {

        CurvePoint curvePoint = curvePointRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid curve point Id:" + id));
        curvePointRepository.delete(curvePoint);
        logger.info("Element Curve supprimé");
        return "redirect:/curvePoint/list";
    }
}
