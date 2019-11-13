//package com.disi.controller;
//
//import com.disi.dto.MenuDTO;
//import com.disi.models.Menu;
//import com.disi.service.MenuService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//import static org.springframework.http.ResponseEntity.*;
//
//@RestController
//@RequestMapping("/menu")
//public class MenuController {
//
//    private final MenuService menuService;
//
//    public MenuController(MenuService menuService) {
//        this.menuService = menuService;
//    }
//
//    @GetMapping("/all")
//    public ResponseEntity<List<Menu>> getAll() {
//
//        return ok().body(menuService.getAll());
//    }
//
//    @GetMapping("/{menuId}")
//    public ResponseEntity<Menu> findById(@PathVariable("menuId") Integer menuId) {
//
//        return ok().body(menuService.findById(menuId));
//    }
//
//    @PutMapping("/{menuId}")
//    public ResponseEntity<Menu> editMenuItemById(@PathVariable("menuId") Integer menuId, @RequestBody MenuDTO menuDTO) {
//
//        Menu menu = menuService.editMenuItemById(menuDTO,menuId);
//        return ok().body(menu);
//    }
//
//    @DeleteMapping("/{menuId}")
//    public ResponseEntity deleteMenuItem(@PathVariable("menuId") Integer menuId) {
//
//        menuService.deleteMenuItemById(menuId);
//
//        return noContent().build();
//    }
//
//    @PostMapping("/add")
//    public ResponseEntity<Menu> addMenuItem(@RequestBody MenuDTO menuDTO) {
//
//        Menu menu = menuService.addMenuItem(menuDTO);
//
//        return ok().body(menu);
//    }
//}
