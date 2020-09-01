package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
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
 * Controller permettant de visualiser, d'ajouter, modifier et supprimer des elements a RuleName.
 *
 */

@Controller
public class RuleNameController {

    private static final Logger logger = LogManager.getLogger(RuleNameController.class);

    private final RuleNameRepository ruleNameRepository;

    public RuleNameController(RuleNameRepository ruleNameRepository) {
        this.ruleNameRepository = ruleNameRepository;
    }


    /**
     *
     * @param model : parametre a a transmettre au Modele pour exposition a la vue : Liste de rulename.
     * @return : Retour de la page html.
     */

    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        model.addAttribute("rules", ruleNameRepository.findAll());
        return "ruleName/list";
    }

    /**
     *
     * @param bid : parametre a a transmettre au Modele pour exposition a la vue add de rulename.
     * @return : retour de la pgae html
     */

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        return "ruleName/add";
    }

    /**
     *
     * @param ruleName : instace de l'Objet rating pour validation des contraintes de format
     * @param result : resultat de la validation
     * @param model : parametre a transmettre au Modele pour exposition a la vue : Liste de rating.
     * @return : Retour de la page html.
     */

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {

        // Verifie que les datas dont valides et sinon return sur la page d'ajout de données
        if (!result.hasErrors()) {

            // Ajout des elements en BDD et redirection vers Bid List
            ruleNameRepository.save(ruleName);
            logger.info("Element Rule ajouté a la BDD");
            model.addAttribute("rules", ruleNameRepository.findAll());
            return "redirect:/ruleName/list";
        }
        logger.info("Format non Valide");
        return "ruleName/add";
    }

    /**
     *
     * @param id : attribut du Modele rulename
     * @param model : parametre a transmettre au Modele pour exposition a la vue : Liste de rulename.
     * @return : retour de la page html
     */

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        RuleName ruleName = ruleNameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rule Id:" + id));
        model.addAttribute("ruleName", ruleName);

        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {

        if (result.hasErrors()) {
            logger.info("Format non Valide");
            return "ruleName/update";
        }
        ruleName.setId(id);
        ruleNameRepository.save(ruleName);
        logger.info("Element Rule mis a jour en BDD");
        model.addAttribute("ruleName", ruleNameRepository.findAll());

        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {

        RuleName ruleName = ruleNameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
        ruleNameRepository.delete(ruleName);
        logger.info("Element Rule supprimé");

        return "redirect:/ruleName/list";
    }
}
