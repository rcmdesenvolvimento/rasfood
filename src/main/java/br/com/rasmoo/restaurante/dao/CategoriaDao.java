package br.com.rasmoo.restaurante.dao;

import br.com.rasmoo.restaurante.entity.Cardapio;
import br.com.rasmoo.restaurante.entity.Categoria;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoriaDao {

    private EntityManager entityManager;

    public CategoriaDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(final Categoria categoria) {
        this.entityManager.persist(categoria);
        System.out.println("Categoria cadastrada com sucesso " + categoria);
    }

    public Categoria consultar(final Integer id) {
        return this.entityManager.find(Categoria.class, id);
    }

    public List<Categoria> listarTodos() {
        String sql = "SELECT c FROM Categoria";
        return entityManager.createQuery(sql, Categoria.class).getResultList();
    }

    public void atualizar(final Categoria categoria) {
        extracted(categoria);
        System.out.println("Categoria alterada com sucesso " + categoria);
    }

    private void extracted(Categoria categoria) {
        this.entityManager.merge(categoria);
    }

    public void excluir(final Categoria categoria) {
        this.entityManager.remove(categoria);
        System.out.println("Categoria excluída com sucesso " + categoria);
    }

}
