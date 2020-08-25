package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
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
 * Controller permettant de visualiser, d'ajouter, modifier et supprimer des elements a la BidList.
 *
 */

@Controller
public class BidListController {

    private static final Logger logger = LogManager.getLogger(BidListController.class);

    private final BidListRepository bidListRepository;

    public BidListController(BidListRepository bidListRepository) {
        this.bidListRepository = bidListRepository;
    }

    /**
     *
     * @param model : parametre a a transmettre au Modele pour exposition a la vue : Liste de bidlist.
     * @return : Retour de la page html.
     */

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        model.addAttribute("bidLists", bidListRepository.findAll());
        return "bidList/list";
    }

    /**
     *
     * @param bid : parametre a a transmettre au Modele pour exposition a la vue add de bidlist.
     * @return : retour de la pgae html
     */

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }


    /**
     *
     * @param bid : instace de l'Objet Bidlist pour validation des contraintes de format
     * @param result : resultat de la validation
     * @param model : parametre a transmettre au Modele pour exposition a la vue : Liste de bidlist.
     * @return : Retour de la page html.
     */

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {

        // Verifie que les datas dont valides et sinon return sur la page d'ajout de données
        if (!result.hasErrors()) {

            // Ajout des elements en BDD et redirection vers Bid List
            bidListRepository.save(bid);
            logger.info("Element BidList ajouté a la BDD");
            model.addAttribute("bidlists", bidListRepository.findAll());
            return "redirect:/bidList/list";
        }
        logger.info("Format non Valide");
        return "bidList/add";
    }

    /**
     *
     * @param id : attribut du Modele Bidlist
     * @param model : parametre a transmettre au Modele pour exposition a la vue : Liste de bidlist.
     * @return : retour de la page html
     */

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) throws Exception {

            BidList bidList = bidListRepository.findByBidListId(id);
            model.addAttribute("bidList", bidList);
            return "bidList/update";
    }


    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {

        if (result.hasErrors()) {
            logger.info("Format non Valide");
            return "bidList/update";
        }
        bidList.setBidListId(id);
        bidListRepository.save(bidList);
        logger.info("Element BidList mis a jour en BDD");
        model.addAttribute("bidList", bidListRepository.findAll());
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {

        BidList bidlist = bidListRepository.findByBidListId(id);
        bidListRepository.delete(bidlist);
        logger.info("Element BidList supprimé");
        return "redirect:/bidList/list";
    }
}
