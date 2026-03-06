package dao;

import jakarta.persistence.EntityManager;
import modelo.Aluno;

import java.util.List;

public class AlunoDao {

    private EntityManager em;

    public AlunoDao(EntityManager em){
        this.em = em;
    }

    public void cadastrar(Aluno aluno){
        em.persist(aluno);
    }

    public List<Aluno> listarTodos(){
        String jpql = "SELECT a FROM Aluno a";
        return em.createQuery(jpql, Aluno.class)
                .getResultList();
    }

    public Aluno buscarPorId(Long id){
        return em.find(Aluno.class, id);
    }

    public void remover(Aluno aluno){
        em.remove(aluno);
    }

}
