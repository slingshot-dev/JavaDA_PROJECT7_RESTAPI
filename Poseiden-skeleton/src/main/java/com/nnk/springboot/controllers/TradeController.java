package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
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
 * Controller permettant de visualiser, d'ajouter, modifier et supprimer des elements a trade.
 *
 */

@Controller
public class TradeController {

    private static final Logger logger = LogManager.getLogger(TradeController.class);

    private final TradeRepository tradeRepository;

    public TradeController(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    /**
     *
     * @param model : parametre a a transmettre au Modele pour exposition a la vue : Liste de trade.
     * @return : Retour de la page html.
     */

    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        model.addAttribute("trades", tradeRepository.findAll());
        return "trade/list";
    }

    /**
     *
     * @param bid : parametre a a transmettre au Modele pour exposition a la vue add de trade.
     * @return : retour de la pgae html
     */

    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
        return "trade/add";
    }

    /**
     *
     * @param trade : instace de l'Objet trade pour validation des contraintes de format
     * @param result : resultat de la validation
     * @param model : parametre a transmettre au Modele pour exposition a la vue : Liste de trade.
     * @return : Retour de la page html.
     */

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {

        // Verifie que les datas dont valides et sinon return sur la page d'ajout de données
        if (!result.hasErrors()) {

            // Ajout des elements en BDD et redirection vers Bid List
            tradeRepository.save(trade);
            logger.info("Element Trade ajouté a la BDD");
            model.addAttribute("trades", tradeRepository.findAll());
            return "redirect:/trade/list";
        }
        logger.info("Format non Valide");
        return "trade/add";
    }

    /**
     *
     * @param id : attribut du Modele trade
     * @param model : parametre a transmettre au Modele pour exposition a la vue : Liste de trade.
     * @return : retour de la page html
     */

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        Trade trade = tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
        model.addAttribute("trade", trade);

        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {

        if (result.hasErrors()) {
            logger.info("Format non Valide");
            return "trade/update";
        }
        trade.setTradeId(id);
        tradeRepository.save(trade);
        logger.info("Element Trade mis a jour en BDD");
        model.addAttribute("trades", tradeRepository.findAll());

        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {

        Trade trade = tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
        tradeRepository.delete(trade);
        logger.info("Element Trade supprimé");

        return "redirect:/trade/list";
    }
}
