package br.com.rasmoo.restaurante.dao;

import br.com.rasmoo.restaurante.entity.Cardapio;
import br.com.rasmoo.restaurante.entity.Categoria;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CardapioDao {

    private EntityManager entityManager;

    public CardapioDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(final Cardapio cardapio) {
        this.entityManager.persist(cardapio);
        System.out.println("cardápio cadastrado com sucesso " + cardapio);
    }

    public Cardapio consultarPorId(final Integer id) {
        try {
            return this.entityManager.find(Cardapio.class, id);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Cardapio> consultarTodos() {
        try {
            String sql = "SELECT c FROM Cardapio c";
            return this.entityManager.createQuery(sql, Cardapio.class).getResultList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public List<Cardapio> consultarPorValor(BigDecimal filtro) {

        try {
            String sql = "SELECT c FROM Cardapio c WHERE c.valor = :valor";
            return this.entityManager.createQuery(sql, Cardapio.class)
                    .setParameter("valor", filtro)
                    .getResultList();
        } catch (Exception e) {
            return Collections.emptyList();
        }

    }

    public Cardapio consultarPorNome(final String filtro) {
        try {
            String sql = "SELECT c FROM Cardapio c WHERE UPPER(c.nome) = UPPER(:nome)";
            return this.entityManager.createQuery(sql, Cardapio.class)
                    .setParameter("nome", filtro)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public void atualizar(final Cardapio cardapio) {
        this.entityManager.merge(cardapio);
        System.out.println("Prato alterado com sucesso " + cardapio);
    }

    public void excluir(final Cardapio cardapio) {
        this.entityManager.remove(cardapio);
        System.out.println("Prato excluído com sucesso " + cardapio);
    }


}
