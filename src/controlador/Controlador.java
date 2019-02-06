package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import net.parisotto.Cliente;
import net.parisotto.Curso;
import net.parisotto.Pagamento;
import net.parisotto.PagamentoId;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class Controlador
 */
@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAApp"); 
	EntityManager em = emf.createEntityManager();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controlador() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void doGet (HttpServletRequest req,HttpServletResponse resp ) throws ServletException, IOException{
    	this.doPost(req, resp);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		int idformulario;
		int tipoformulario;
		String cpfmascara,nomecurso,valorcurso,url;
		long cpf,cdcurso,valor;
		String nome,email,datainscricao;
		
		idformulario = Integer.parseInt(request.getParameter("idformulario"));
		tipoformulario = Integer.parseInt(request.getParameter("tipoformulario"));
		EntityTransaction tx = em.getTransaction();
		HttpSession session = request.getSession();
		
		if(idformulario == 1) {
			switch(tipoformulario) {
				case 11:
					TypedQuery<Cliente> query = em.createQuery("" + "Select c FROM Cliente c",Cliente.class);
					List<Cliente> clientes = query.getResultList();
					session.setAttribute("mensagem", "Total de clintes " + clientes.size() + " cadatrado");
					session.setAttribute("clientes",clientes);
					response.sendRedirect("clientes/consultaTodos.jsp");
				
					break;
				case 12:{
					cpfmascara = request.getParameter("cpf");
					cpfmascara = cpfmascara.replaceAll("[./]", "");
					cpf = Long.parseLong(cpfmascara);
					out.println("<h2> clientes ==> consultar um cliente ==>" + cpf);
					
					Cliente cliente = em.find(Cliente.class,cpf);
					
					if(cliente != null) {
						session.setAttribute("mensagem", "cliente "+cpf+"econcotrado" );
						session.setAttribute("cliente",cliente);
					}else {
						session.setAttribute("mensagem", "Clienet " + cpf + "nao econtrado");
						session.setAttribute("cliente",null);
					}
					response.sendRedirect("clientes/resultado.jsp");
					
					break;
				}case 13:{
					cpfmascara = request.getParameter("cpf");
					cpfmascara = cpfmascara.replaceAll("[./]", "");
					cpf = Long.parseLong(cpfmascara);
					nome = request.getParameter("nome");
					email = request.getParameter("email");
					Cliente cliente = em.find(Cliente.class,cpf);
					session.setAttribute("mensagem", "cliente "+cpf+" cadastrado" );
					session.setAttribute("cliente",cliente);
					tx.begin();
					em.remove(cliente);
					tx.commit();
					session.setAttribute("mensagem", "Clienet " + cpf + " cadatrado");
					session.setAttribute("cliente",cliente);
					response.sendRedirect("clientes/resultado.jsp");
					break;
				}case 14:{ 
					cpfmascara = request.getParameter("cpf");
					cpfmascara = cpfmascara.replaceAll("[./]", "");
					cpf = Long.parseLong(cpfmascara);
					nome = request.getParameter("nome");
					email = request.getParameter("email");
					Cliente cliente = em.find(Cliente.class,cpf);
					
					if(cliente != null) {
						cliente = em.find(Cliente.class,cpf);
						tx.begin();
						em.merge(cliente);
						tx.commit();
						session.setAttribute("mensagem", "cliente "+cpf+" alterado" );
						session.setAttribute("cliente",cliente);
					}else {
						session.setAttribute("mensagem", "Clienet " + cpf + " não alterado");
						session.setAttribute("cliente",null);
					}
					response.sendRedirect("clientes/resultado.jsp");
					
					break;	
				}case 15:{
					cpfmascara = request.getParameter("cpf");
					cpfmascara = cpfmascara.replaceAll("[./]", "");
					cpf = Long.parseLong(cpfmascara);
					out.println("<h2> clientes ==> Excluir ==>"+ cpf);
					Cliente cliente = em.find(Cliente.class,cpf);
					
					if(cliente != null) {
						tx.begin();
						em.remove(cliente);
						tx.commit();
						session.setAttribute("mensagem", "cliente "+cpf+" excluido" );
						
					}else {
						session.setAttribute("mensagem", "Clienet " + cpf + "nao econtrado");		
					}
					session.setAttribute("cliente",null);
					response.sendRedirect("clientes/resultado.jsp");
					break;
				}
				}
			
		}else if(idformulario == 2) {	
			switch(tipoformulario) {
				case 21:
					TypedQuery<Curso> query = em.createQuery("" + "Select c FROM Curso c",Curso.class);
					List<Curso> cursos = query.getResultList();
					session.setAttribute("mensagem", "Total de cursos " + cursos.size() + " cadatrado");
					session.setAttribute("cursos",cursos);
					response.sendRedirect("cursos/consultaTodos.jsp");
				
					break;
				case 22:{
					cdcurso = Long.parseLong(request.getParameter("cdcurso"));
					Curso curso = em.find(Curso.class,cdcurso);
					
					if(curso != null) {
						session.setAttribute("mensagem", "cursos "+cdcurso+"econcotrado" );
						session.setAttribute("cursos",curso);
					}else {
						session.setAttribute("mensagem", "cursos " + cdcurso + "nao econtrado");
						session.setAttribute("cursos",null);
					}
					response.sendRedirect("clientes/resultado.jsp");
					break;
				}case 23:{
					cdcurso = Long.parseLong(request.getParameter("cdcurso"));
					nomecurso = request.getParameter("nome");
					valorcurso = request.getParameter("valor");
					valor = Long.parseLong(valorcurso);
					url = request.getParameter("site");
					
					
						
					Curso curso = new Curso(cdcurso,nomecurso,valor,url);
					tx.begin();	
					em.persist(curso);
					tx.commit();
					session.setAttribute("mensagem", "Clienet " + cdcurso + " cadatrado");
					session.setAttribute("cusos",curso);
					response.sendRedirect("cursos/resultado.jsp");
					break;
				}case 24:{ 
					cdcurso = Long.parseLong(request.getParameter("cdcurso"));
					nomecurso = request.getParameter("nome");
					valorcurso = request.getParameter("valor");
					valor = Long.parseLong(valorcurso);
					url = request.getParameter("site");
					Curso curso = em.find(Curso.class,cdcurso);
					
					if(curso != null) {
					    curso = new Curso(cdcurso,nomecurso,valor,url);
						tx.begin();
						em.merge(curso);
						tx.commit();
						session.setAttribute("mensagem", "curso" +cdcurso+" alterado" );
						session.setAttribute("curso",curso);
					}else {
						session.setAttribute("curso", "curso " + cdcurso + " não alterado");
						session.setAttribute("curso",null);
					}
					response.sendRedirect("cursos/resultado.jsp");
					break;	
				}case 25:{
					cdcurso = Long.parseLong(request.getParameter("cdcurso"));
					Curso curso = em.find(Curso.class,cdcurso);
					if(curso != null) {
						tx.begin();
						em.remove(cdcurso);
						tx.commit();
						session.setAttribute("mensagem", "curso "+cdcurso+" excluido" );
						
					}else {
						session.setAttribute("mensagem", "cdcurso " + cdcurso + "nao econtrado");		
					}
					session.setAttribute("curso",null);
					response.sendRedirect("cursos/resultado.jsp");
					break;
				}
			}
		}else if(idformulario == 3) {
			switch(tipoformulario) {
			case 31:
				TypedQuery<Pagamento> query = em.createQuery("" + "Select c FROM Pagamento c",Pagamento.class);
				List<Pagamento> pagamentos = query.getResultList();
				session.setAttribute("mensagem", "Total de Pagamento " + pagamentos.size() + " cadatrado");
				session.setAttribute("pagamentos",pagamentos);
				response.sendRedirect("pagamentos/consultaTodos.jsp");;
			
				break;
			case 32:{
				cpfmascara = request.getParameter("cpf");
				cpfmascara = cpfmascara.replaceAll("[./]", "");
				cpf = Long.parseLong(cpfmascara);
				cdcurso = Long.parseLong(request.getParameter("cdcurso"));
				
				PagamentoId pgtoid = new PagamentoId(cpf,cdcurso); 
				Pagamento pagamento = em.find(Pagamento.class, pgtoid);
				if(pagamento != null) {
					session.setAttribute("mensagem", "pagamento "+cpf+"econcotrado" );
					session.setAttribute("pagamento",pagamento);
				}else {
					session.setAttribute("mensagem", "Clienet " + cpf + "nao econtrado");
					session.setAttribute("pagamento",null);
				}
				response.sendRedirect("pagamentos/resultado.jsp");
				
				break;
			}case 33:{
				cpfmascara = request.getParameter("cpf");
				cpfmascara = cpfmascara.replaceAll("[./]", "");
				cpf = Long.parseLong(cpfmascara);
				cdcurso = Long.parseLong(request.getParameter("cdcurso"));
				datainscricao = request.getParameter("datainscricao");
				DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate date = LocalDate.parse(datainscricao,formater);
				DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				PagamentoId pgtoid = new PagamentoId(cpf,cdcurso); 
				Pagamento pagamento = new Pagamento(pgtoid,fmt.format(date));
				tx.begin();
				em.persist(pagamento);
				tx.commit();
				session.setAttribute("mensagem", "pagamento " + cpf + " cadatrado");
				session.setAttribute("pagamentos",pagamento);
				response.sendRedirect("pagamentos/resultado.jsp");
				break;}
			case 34:{ 
				cpfmascara = request.getParameter("cpf");
				cpfmascara = cpfmascara.replaceAll("[./]", "");
				cpf = Long.parseLong(cpfmascara);
				cdcurso = Long.parseLong(request.getParameter("cdcurso"));
				datainscricao = request.getParameter("datainscricao");
				DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate date = LocalDate.parse(datainscricao,formater);
				DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				PagamentoId pgtoid = new PagamentoId(cpf,cdcurso); 
				Pagamento pagamento = new Pagamento(pgtoid,fmt.format(date));
				pagamento = em.find(Pagamento.class, pgtoid);
				if(pagamento != null) {
					pagamento = new Pagamento(pgtoid,fmt.toString());
					tx.begin();
					em.merge(pagamento);
					tx.commit();
					session.setAttribute("mensagem", "pagamento" +cdcurso+" alterado" );
					session.setAttribute("curso",pagamento);
				}else {
					session.setAttribute("pagamento", "pagamento " + cdcurso + " não alterado");
					session.setAttribute("pagamento",null);
				}
				response.sendRedirect("pagamentos/resultado.jsp");
				
				break;	}
			case 35:{
				cpfmascara = request.getParameter("cpf");
				cpfmascara = cpfmascara.replaceAll("[./]", "");
				cpf = Long.parseLong(cpfmascara);
				cdcurso = Long.parseLong(request.getParameter("cdcurso"));
				PagamentoId pgtoid = new PagamentoId(cpf,cdcurso); 
				Pagamento pagamento = em.find(Pagamento.class, pgtoid);
				if(pagamento != null) {
					tx.begin();
					em.remove(pagamento);
					tx.commit();
					session.setAttribute("mensagem", "pagamento "+pagamento+" excluido" );
					
				}else {
					session.setAttribute("mensagem", "pagamento " + pagamento + "nao econtrado");		
				}
				session.setAttribute("pagamento",null);
				response.sendRedirect("pagamentos/resultado.jsp");
				break;
			}
			}
		}
	}

}
