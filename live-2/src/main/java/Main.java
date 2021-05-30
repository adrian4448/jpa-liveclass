import com.prJPA.dtos.UsuarioDTO;
import com.prJPA.model.Dominio;
import com.prJPA.model.Usuario;

import javax.persistence.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CursoJPA-PU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // primeirasConsulta(entityManager);

        // escolhendoORetorno(entityManager);

        // fazendoProjecoes(entityManager);

        passandoParametros(entityManager);

        entityManagerFactory.close();
        entityManager.close();
    }

    public static void primeirasConsulta(EntityManager entityManager) {
        //retornar todos os resultados da query
        String jpql = "select u from Usuario u";
        TypedQuery<Usuario> typedQuery = entityManager.createQuery(jpql, Usuario.class);
        List<Usuario> usuarios = typedQuery.getResultList();
        usuarios.forEach(u -> System.out.println(u.getId() + " , " + u.getNome()));

        //query que retorna somente um resultado (TIPADA)
        String jpqlSingle = "select u from Usuario u where u.id= 1";
        TypedQuery<Usuario> typedQuerySingle = entityManager.createQuery(jpqlSingle, Usuario.class);
        Usuario usuario = typedQuerySingle.getSingleResult();
        System.out.println(usuario.getId() + " - " + usuario.getNome());

        //query que retorna somente um resultado (NAO TIPADA)
        String jpqlCast = "select u from Usuario u where u.id = 2";
        Query query = entityManager.createQuery(jpqlCast);
        Usuario usuarioQuery = (Usuario) query.getSingleResult();
        System.out.print(usuarioQuery.getId() + " - " + usuarioQuery.getNome());
    }

    public static void escolhendoORetorno(EntityManager entityManager) {
        //Selecionando um atributo da classe Usuario
        String jpql = "select u.dominio from Usuario u where u.id = 1";
        TypedQuery<Dominio> typedQuery = entityManager.createQuery(jpql, Dominio.class);
        Dominio dominio = typedQuery.getSingleResult();
        System.out.println(dominio.getId() + " - " + dominio.getNome());

        //Selecionando um atributo Simples (Tipagem "primitiva") da classe usuario
        String jpqlNomes = "select u.nome from Usuario u";
        TypedQuery<String> typedQueryStr = entityManager.createQuery(jpqlNomes, String.class);
        List<String> nomes = typedQueryStr.getResultList();
        nomes.forEach(n -> System.out.println("Nome: " + n));
    }

    public static void fazendoProjecoes(EntityManager entityManager) {
        //pegar varias propiedades distintas sem usar um objeto DTO (poderia ser sem o format, com o arr[0], arr[1], arr[2])
        String jpqlArr = "select u.id, u.nome, u.login from Usuario u";
        TypedQuery<Object[]> typedQueryArr = entityManager.createQuery(jpqlArr, Object[].class);
        List<Object[]> listaArr_ = typedQueryArr.getResultList();
        listaArr_.forEach(arr -> System.out.println(String.format("%s, %s, %s", arr)));

        //pegar varias propiedads distintas com classe DTO (new instancia a classe com JPA, e deve ser passado pacote da classe mais ela (Deve possuir construtor com parametros que deseja buscar))
        String jpqlDTO = "select new com.prJPA.dtos.UsuarioDTO(id, login, nome) " +
                "from Usuario";
        TypedQuery<UsuarioDTO> typedQueryDTO = entityManager.createQuery(jpqlDTO, UsuarioDTO.class);
        List<UsuarioDTO> usuariosDTO = typedQueryDTO.getResultList();
        usuariosDTO.forEach(u -> System.out.println("DTO: " + u.getId() + " - " + u.getNome()));
    }

    public static void passandoParametros(EntityManager entityManager) {
        //Passando parametro pelo nome
        String jpql = "select u from Usuario u where u.id = :idUsuario";
        TypedQuery<Usuario> typedQuery = entityManager.createQuery(jpql, Usuario.class)
                .setParameter("idUsuario", 2L);
        Usuario user = typedQuery.getSingleResult();
        System.out.println(user.getId() + " - " + user.getNome());

        //Passando 2 parametro
        String jpqlIndice = "select u from Usuario u where u.id = :idUsuario and u.nome = :nomeUsuario";
        TypedQuery<Usuario> typedQueryIndice = entityManager.createQuery(jpqlIndice, Usuario.class);
        typedQueryIndice.setParameter("idUsuario" , 1L);
        typedQueryIndice.setParameter("nomeUsuario", "Cal Lightman");
        Usuario usuarioIndice = typedQueryIndice.getSingleResult();
        System.out.println(usuarioIndice.getId() + " - " + usuarioIndice.getNome());

    }
}
