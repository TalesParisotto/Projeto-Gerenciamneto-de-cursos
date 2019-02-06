<html><head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="lib/js/jquery.min.js"></script>
    <script type="text/javascript" src="lib/js/bootstrap.min.js"></script>
    <link href="lib/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="lib/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="lib/css/padrao.css" rel="stylesheet" type="text/css">
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
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-12 text-center">
            <h3 class="tt_menu">&gt;&gt; AUTENTICAÇÃO &lt;&lt;</h3>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12">
            <form class="form-horizontal" role="form" action="util/autenticacao.jsp" method="post">
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="cpf" class="control-label">CPF: </label>
                </div>
                <div class="col-sm-10">
                  <input type="number" name="cpf" class="form-control" id="cpf" placeholder="CPF" required>
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputSenha" class="control-label">Senha: </label>
                </div>
                <div class="col-sm-10">
                  <input type="password" name="senha" class="form-control" id="inputSenha" placeholder="Senha">
                </div>
              </div>
              <button type="submit" class="btn btn-danger">Login</button>
            </form>
          </div>
        </div>
      </div>
    </div>
    <p style="text-align:center; color:red">${mensagem}</p>
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-12 text-center corrigir">
            <a class="btn btn-default" href="javascript:window.history.go(-1)">Voltar</a>
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
