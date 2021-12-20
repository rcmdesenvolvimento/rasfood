package br.com.rasmoo.restaurante.service.teste;

import br.com.rasmoo.restaurante.dao.CardapioDao;
import br.com.rasmoo.restaurante.dao.CategoriaDao;
import br.com.rasmoo.restaurante.entity.Cardapio;
import br.com.rasmoo.restaurante.entity.Categoria;
import br.com.rasmoo.restaurante.util.JPAUtil;
import org.w3c.dom.ls.LSOutput;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CardapioService {

    public static void main(String[] args) {

        EntityManager entityManager = JPAUtil.getEntityManagerRasFood();
        cadastrarCardapio(entityManager, cadastrarCategoria(entityManager));


    }

    private static Categoria cadastrarCategoria(EntityManager entityManager) {

        CategoriaDao categoriaDao = new CategoriaDao(entityManager);
        Categoria categoria = new Categoria("Prato Principal");
        entityManager.getTransaction().begin();
        categoriaDao.cadastrar(categoria);
        entityManager.getTransaction().commit();
        entityManager.clear();

        return categoria;
    }

    private static void cadastrarCardapio(EntityManager entityManager, Categoria categoria) {

        Cardapio cardapio = new Cardapio();
        cardapio.setNome("Risoto de frutos do mar");
        cardapio.setDescricao("Risoto acompanhado de lula, polvo e mariscos");
        cardapio.setDisponivel(true);
        cardapio.setValor(BigDecimal.valueOf(88.50));
        cardapio.setCategoria(categoria);

        Cardapio cardapio2 = new Cardapio();
        cardapio2.setNome("Salmão");
        cardapio2.setDescricao("Salmão grelhado");
        cardapio2.setDisponivel(true);
        cardapio2.setValor(BigDecimal.valueOf(68.50));
        cardapio.setCategoria(categoria);

        CardapioDao cardapioDao = new CardapioDao(entityManager);

        // Inclusão
        entityManager.getTransaction().begin();
        cardapioDao.cadastrar(cardapio);
        cardapioDao.cadastrar(cardapio2);
        entityManager.getTransaction().commit();

        //cardapioDao.consultarTodos().forEach(item -> System.out.println("Prato(s) consultado foi :" + item));

        //System.out.println("Pesquisa por valor " + cardapioDao.consultarPorValor(BigDecimal.valueOf(68.50)));

        System.out.println("Pesquisa por Nome " + cardapioDao.consultarPorNome("salmão"));

        entityManager.close();

    }
}


