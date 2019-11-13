//package com.disi.service;
//
//import com.disi.dto.MenuDTO;
//import com.disi.errorHandler.ResourceNotFoundException;
//import com.disi.models.Menu;
//import com.disi.repository.MenuRepository;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class MenuService {
//
//    private final MenuRepository menuRepository;
//    private final ModelMapper modelMapper;
//
//    public MenuService(MenuRepository menuRepository, ModelMapper modelMapper) {
//        this.menuRepository = menuRepository;
//        this.modelMapper = modelMapper;
//    }
//
//    public List<Menu> getAll() {
//
//        return menuRepository.findAll();
//
//    }
//
//    public Menu findById(Integer menuId) {
//        Optional<Menu> menuItem = menuRepository.findById(menuId);
//        if (menuItem.isPresent()) {
//
//            return menuItem.get();
//        } else {
//            throw new ResourceNotFoundException(String.format("No item menu with id %s was found", menuId));
//        }
//    }
//
//    public Menu addMenuItem(MenuDTO menuDto) {
//
//        Menu menu = modelMapper.map(menuDto, Menu.class);
//        return menuRepository.save(menu);
//    }
//
//    public Menu editMenuItemById(MenuDTO menuDto, int id) {
//
//        findById(id);
//        Menu menu = modelMapper.map(menuDto, Menu.class);
//        menu.setId(id);
//        return menuRepository.save(menu);
//    }
//
//    public void deleteMenuItemById(int id) {
//
//        menuRepository.deleteById(id);
//    }
//}
