package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.poly.beecinema.entity.Suatchieu;
import vn.edu.poly.beecinema.entity.Ve;
import vn.edu.poly.beecinema.service.SuatChieuService;
import vn.edu.poly.beecinema.service.VeService;

import java.util.List;

@Controller
@RequestMapping("/admin/ticket")
public class TicketController {
    @Autowired
    private VeService veService;
    @GetMapping("/show-ticket")
    public String showTicket(Model model){
        String keyword = null;
        return listByPage(model, 1, "suatchieu.ngaychieu", "desc", keyword);
    }

    @GetMapping("/page/{pageNumber}")
    public String listByPage(Model model ,
                             @PathVariable("pageNumber") int currentPage,
                             @Param("sortField") String sortField,
                             @Param("sortDir") String sortDir,
                             @Param("keyword") String keyword) {
        Page<Ve> page = veService.listAll(currentPage, sortField, sortDir, keyword);
        long totalItem = page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<Ve> ve = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("ve", ve);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);
        return "admin/ticket/show-ticket";
    }
}
