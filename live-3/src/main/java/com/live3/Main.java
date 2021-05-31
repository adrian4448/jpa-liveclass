package com.live3;

import com.live3.model.Configuracao;
import com.live3.model.Usuario;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CursoJPA-PU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        trabalhandoComJoins(entityManager);

        fazendoLeftJoin(entityManager);

        carregamentoComJoinFetch(entityManager);

        filtrandoRegistros(entityManager);

        utilizandoOperadoresLogicos(entityManager);

        utilizandoOperadorIn(entityManager);

        ordenandoResultados(entityManager);

        paginanddoResultados(entityManager);

        entityManagerFactory.close();
        entityManager.close();
    }

    public static void trabalhandoComJoins(EntityManager entityManager) {
        //Join feito através do atributo dominio presente na classe Usuario
        String jpql = "select u from Usuario u inner join u.dominio d where d.id = 1";
        TypedQuery<Usuario> typedQuery = entityManager.createQuery(jpql, Usuario.class);
        List<Usuario> usuarios = typedQuery.getResultList();
        usuarios.forEach(u -> System.out.println(u.getId() + " - " + u.getNome() + " - Dominio: " + u.getDominio().getNome()));

        //Join feito através do atributo configuracao presente na classe Usuario
        String jpql2 = "select u from Usuario u join u.configuracao c";
        TypedQuery<Usuario> typedQuery2 = entityManager.createQuery(jpql2, Usuario.class);
        List<Usuario> usuarios2 = typedQuery2.getResultList();
        usuarios2.forEach(u -> System.out.println("Usuario " + u.getNome() + " Encerrar Sessão Automaticamente : " + u.getConfiguracao().isEncerrarSessaoAutomaticamente()));
    }

    public static void fazendoLeftJoin(EntityManager entityManager) {
        //Left Join realizado em cima da configuração
        String jpql = "SELECT u, c FROM Usuario u LEFT JOIN u.configuracao c";
        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> lista = typedQuery.getResultList();
        lista.forEach(obj ->  {
            String out = ((Usuario) obj[0]).getNome();
            if(obj[1] == null) {
                out += ", NULL";
            }else {
                out += ", " + ((Configuracao) obj[1]).isReceberNotificacoes();
            }
            System.out.println(out);
        });
    }

    public static void carregamentoComJoinFetch(EntityManager entityManager) {
        //Select usando JOIN FETCH (Remove o problema de executar um select de configuração para cada usuario)
        String jpql = "select u from Usuario u join fetch u.configuracao join fetch u.dominio";
        TypedQuery<Usuario> typedQuery = entityManager.createQuery(jpql, Usuario.class);
        List<Usuario> usuarios = typedQuery.getResultList();
        usuarios.forEach(u -> System.out.println(u.getId() + " - " + u.getNome()));
    }

    public static void filtrandoRegistros(EntityManager entityManager) {
        // LIKE, IS NULL, IS EMPTY, BEETWEN, >, <, >=, <=, =, <>
        String jpql = "select u from Usuario u where u.nome LIKE :nomeUsuario";
        TypedQuery<Usuario> typedQuery = entityManager.createQuery(jpql, Usuario.class).setParameter("nomeUsuario", "Cal%");
        List<Usuario> usuarios = typedQuery.getResultList();
        usuarios.forEach(u -> System.out.println(u.getId() + " - " + u.getNome()));
    }

    public static void utilizandoOperadoresLogicos(EntityManager entityManager) {
        String jpql = "select u from Usuario u where u.id <= 2";
        TypedQuery<Usuario> typedQuery = entityManager.createQuery(jpql, Usuario.class);
        List<Usuario> usuarios = typedQuery.getResultList();
        usuarios.forEach(u -> System.out.println(u.getId() + " - " + u.getNome()));
    }

    public static void utilizandoOperadorIn(EntityManager entityManager) {
        //Ao utilizar o Like, passar um array para preencher os parametros
        String jpql = "select u from Usuario u where u.id in (:ids)";
        TypedQuery<Usuario> typedQuery = entityManager.createQuery(jpql, Usuario.class).setParameter("ids", Arrays.asList(1,2,3));
        List<Usuario> usuarios = typedQuery.getResultList();
        usuarios.forEach(u -> System.out.println(u.getId() + " - " + u.getNome()));
    }

    public static void ordenandoResultados(EntityManager entityManager) {
        // Pode ordenar pelo nome do dominio, acessando o dominio do usuario e logo após a propiedade nome (Patch Expression)
        String jpql  = "select u from Usuario u order by u.dominio.nome";
        TypedQuery<Usuario> typedQuery = entityManager.createQuery(jpql, Usuario.class);
        List<Usuario> usuarios = typedQuery.getResultList();
        usuarios.forEach(u -> System.out.println(u.getId() + " - " + u.getNome()));
    }

    public static void paginanddoResultados(EntityManager entityManager) {
        //firstResult = número da página (exemplo, pega o registro 1) maxResult = pega a quantidade de registros informada apartir do firstResult (pega dois registros apartir do 1)
        String jpql = "select u from Usuario u";
        TypedQuery<Usuario> typedQuery = entityManager.createQuery(jpql, Usuario.class).setFirstResult(1).setMaxResults(2);
        List<Usuario> usuarios = typedQuery.getResultList();
        usuarios.forEach(u -> System.out.println(u.getId() + " - " + u.getNome()));
    }
}
