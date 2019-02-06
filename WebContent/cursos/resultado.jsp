<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../util/topo.jsp"/>
<html><head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="../lib/js/jquery.min.js"></script>
    <script type="text/javascript" src="../lib/js/bootstrap.min.js"></script>
    <link href="../lib/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="../lib/css/bootstrap.css" rel="stylesheet" type="text/css">
     <link href="../lib/css/padrao.css" rel="stylesheet" type="text/css">
  
  </head><body>
    <div class="section section-danger text-justify">
      <div class="container">
        <div class="row text-center">
          <div class="col-md-12 text-center">
            <h1 class="text-center">Sistema de Gerenciamento de Cursos</h1>
          </div>
        </div>
      </div>
    </div>
    <div class="section section-danger text-justify">
      <div class="container">
        <div class="row text-center">
          <div class="col-md-12 text-center">
           <h1 class="text-center">${mensagem }</h1>
           <c:if test="${ curso != null}">
           		<h3 class="text-center">Cdurso:${curso.cdcurso}</h3>
           		<h3 class="text-center">Nome:${curso.nome}</h3>
           		<h3 class="text-center">url:${curso.url}</h3>
           		<h3 class="text-center">valor:${curso.valor}</h3>
           </c:if>	
          </div>
        </div>
      </div>
    </div>
    
    <p style="text-align:center; color:red">${mensagem}</p>
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-12 text-center">
          	<a class="btn btn-default" href="index.jsp">Voltar para a pagina de cursos</a>      
          </div>
        </div>
      </div>
    </div>
    <footer>
      <div class="navbar navbar-fixed-bottom bgred">
        <div class="container">
          <div class="row">
            <div class="col-sm-12 text-center" style="top:13px;color:#fff;">© Parisotto.Net - Tales Parisotto</div>
          </div>
        </div>
      </div>
    </footer>
  

</body></html>
