package org.example.cliente.service;


import org.example.cliente.entities.Categoria;
import org.example.cliente.repository.CategoriaRepository;




public class CategoriaService {

    private CategoriaRepository categoriaRepository = new CategoriaRepository();
 
    public CategoriaService() {

    }

    public void salvarCategoria(Categoria categoria) {
        categoriaRepository.salvarCategoria(categoria);
    }

    public void deleteCategoria(int categoriaId) {
        categoriaRepository.deleteCategoria(categoriaId);
    }

}
