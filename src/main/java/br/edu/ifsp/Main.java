package br.edu.ifsp;

import dao.AlunoDao;
import modelo.Aluno;
import util.JPAUtil;

import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {

     static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        EntityManager em = JPAUtil.getEntityManager();
        AlunoDao dao = new AlunoDao(em);

        int opcao = -1;

        while(opcao != 0){

            System.out.println("\n===== MENU =====");
            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Listar alunos");
            System.out.println("3 - Buscar aluno por ID");
            System.out.println("4 - Remover aluno");
            System.out.println("0 - Sair");

            opcao = sc.nextInt();
            sc.nextLine();

            switch(opcao){

                case 1:

                    System.out.println("Nome:");
                    String nome = sc.nextLine();

                    System.out.println("RA:");
                    String ra = sc.nextLine();

                    System.out.println("Email:");
                    String email = sc.nextLine();

                    System.out.println("Nota 1:");
                    BigDecimal n1 = sc.nextBigDecimal();

                    System.out.println("Nota 2:");
                    BigDecimal n2 = sc.nextBigDecimal();

                    System.out.println("Nota 3:");
                    BigDecimal n3 = sc.nextBigDecimal();

                    Aluno aluno = new Aluno(nome,ra,email,n1,n2,n3);

                    em.getTransaction().begin();
                    dao.cadastrar(aluno);
                    em.getTransaction().commit();

                    System.out.println("Aluno cadastrado!");

                    break;

                case 2:

                    List<Aluno> alunos = dao.listarTodos();

                    for(Aluno a : alunos){

                        System.out.println("\nID: "+a.getId());
                        System.out.println("Nome: "+a.getNome());
                        System.out.println("RA: "+a.getRa());
                        System.out.println("Email: "+a.getEmail());
                        System.out.println("Média: "+a.getMedia());

                    }

                    break;

                case 3:

                    System.out.println("Digite o ID:");
                    Long id = sc.nextLong();

                    Aluno a = dao.buscarPorId(id);

                    if(a != null){

                        System.out.println("Nome: "+a.getNome());
                        System.out.println("Email: "+a.getEmail());
                        System.out.println("Média: "+a.getMedia());

                    }else{

                        System.out.println("Aluno não encontrado");

                    }

                    break;

                case 4:

                    System.out.println("ID para remover:");
                    Long idRemover = sc.nextLong();

                    Aluno remover = dao.buscarPorId(idRemover);

                    if(remover != null){

                        em.getTransaction().begin();
                        dao.remover(remover);
                        em.getTransaction().commit();

                        System.out.println("Aluno removido!");

                    }

                    break;

            }

        }

        em.close();
        sc.close();

    }

}