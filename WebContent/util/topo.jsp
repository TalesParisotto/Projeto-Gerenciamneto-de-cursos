<% 

if(session.getAttribute("login") != "true") {
	session.setAttribute("mensagem", "Acesso proibido! Autentifique-se.");
	%><jsp:forward page="login.jsp"></jsp:forward><% 
} 

%>
