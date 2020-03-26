<%-- 
    Document   : home
    Created on : 21-oct.-2019, 12:42:48
    Author     : Regis & Yannis
--%>

<%--@page import="org.apache.jasper.runtime.JspRuntimeLibrary.include(ServletRequest, ServletResponse, String, JspWriter, boolean)"--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Home Ferries</title>
<meta name="description" content="">
<meta name="author" content="">

<!-- Favicons
    ================================================== -->
<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
<link rel="apple-touch-icon" href="img/apple-touch-icon.png">
<link rel="apple-touch-icon" sizes="72x72" href="img/apple-touch-icon-72x72.png">
<link rel="apple-touch-icon" sizes="114x114" href="img/apple-touch-icon-114x114.png">
<!-- Bootstrap -->
<link rel="stylesheet" type="text/css"  href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="fonts/font-awesome/css/font-awesome.css">

<!-- Stylesheet
    ================================================== -->
<link href="css/aos.css" rel="stylesheet">
<link rel="stylesheet" type="text/css"  href="css/style.css">
<link href="https://fonts.googleapis.com/css?family=Raleway:300,400,500,600,700" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Rochester" rel="stylesheet">

</head>
<body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">
    
    <%if (session.getAttribute("NumClient") != null)
    {
        %><jsp:include page="headerlogged.jsp" /><%
    }
    else
    {
        %><jsp:include page="headerunlogged.jsp" /><%
    }%>
    <% if (request.getAttribute("NewClient") == "0")
    { request.setAttribute("NewClient", "1"); %>
        <div style=" margin-top: 15px; margin-left: 400px; margin-right: 400px; padding:5px; background-color:#3CB371 ; border:2px solid #000000 ; -moz-border-radius:9px; -khtml-border-radius:9px; -webkit-border-radius:9px; border-radius:9px;">
        <div style=" vertical-align:middle; color: #FFFFFF; font-size: 3em; float: left; width: 40px; margin-right: 5px; height: 20px; padding:3px;"></div>
        <strong style="color: #FFFFFF; text-align: center;">Inscription réussie : Vous allez recevoir un e-mail de confirmation.<br>    Votre numéro de client est le <%=request.getAttribute("NumClient") %> </strong></div>      
    <%}%>
    <% if (request.getAttribute("AchatConfirme") == "0")
    { request.setAttribute("AchatConfirme", "10"); %>
        <div style=" margin-top: 15px; margin-left: 400px; margin-right: 400px; padding:5px; background-color:#3CB371  ; border:2px solid #000000 ; -moz-border-radius:9px; -khtml-border-radius:9px; -webkit-border-radius:9px; border-radius:9px;">
        <div style=" vertical-align:middle; color: #FFFFFF; font-size: 3em; float: left; width: 40px; margin-right: 5px; height: 20px; padding:3px;"></div>
        <strong style="color: #FFFFFF; text-align: center;">Réservation réussie : Vous allez recevoir un e-mail de confirmation.<br>    Votre numéro de réservation est le <%=request.getAttribute("NumRes") %> </strong></div>      
    <%}%>
    <% if (request.getAttribute("AchatConfirme") == "1")
    { request.setAttribute("AchatConfirme", "10"); %>
        <div style=" margin-top: 15px; margin-left: 400px; margin-right: 400px; padding:5px; background-color:#FF0000  ; border:2px solid #000000 ; -moz-border-radius:9px; -khtml-border-radius:9px; -webkit-border-radius:9px; border-radius:9px;">
        <div style=" vertical-align:middle; color: #FFFFFF; font-size: 3em; float: left; width: 40px; margin-right: 5px; height: 20px; padding:3px;"></div>
        <strong style="color: #FFFFFF; text-align: center;">Reservation échouée : Le numéro de carte est incorrecte.</strong></div>      
    <%}%>
    <% if (request.getAttribute("AchatConfirme") == "2")
    { request.setAttribute("AchatConfirme", "10"); %>
        <div style=" margin-top: 15px; margin-left: 400px; margin-right: 400px; padding:5px; background-color:#FF0000  ; border:2px solid #000000 ; -moz-border-radius:9px; -khtml-border-radius:9px; -webkit-border-radius:9px; border-radius:9px;">
        <div style=" vertical-align:middle; color: #FFFFFF; font-size: 3em; float: left; width: 40px; margin-right: 5px; height: 20px; padding:3px;"></div>
        <strong style="color: #FFFFFF; text-align: center;">Reservation échouée : Le débit a échoué. (Solde insuffisant).</strong></div>      
    <%}%>
    <% if (request.getAttribute("AchatConfirme") == "3")
    { request.setAttribute("AchatConfirme", "10"); %>
        <div style=" margin-top: 15px; margin-left: 400px; margin-right: 400px; padding:5px; background-color:#FF0000  ; border:2px solid #000000 ; -moz-border-radius:9px; -khtml-border-radius:9px; -webkit-border-radius:9px; border-radius:9px;">
        <div style=" vertical-align:middle; color: #FFFFFF; font-size: 3em; float: left; width: 40px; margin-right: 5px; height: 20px; padding:3px;"></div>
        <strong style="color: #FFFFFF; text-align: center;">Reservation échouée : Le montant du panier est trop élevé.</strong></div>      
    <%}%>
    <% if (request.getAttribute("AchatConfirme") == "4")
    { request.setAttribute("AchatConfirme", "10"); %>
        <div style=" margin-top: 15px; margin-left: 400px; margin-right: 400px; padding:5px; background-color:#FF0000  ; border:2px solid #000000 ; -moz-border-radius:9px; -khtml-border-radius:9px; -webkit-border-radius:9px; border-radius:9px;">
        <div style=" vertical-align:middle; color: #FFFFFF; font-size: 3em; float: left; width: 40px; margin-right: 5px; height: 20px; padding:3px;"></div>
        <strong style="color: #FFFFFF; text-align: center;">Reservation échouée : Le nom/prénom du client ne correspond pas.</strong></div>      
    <%}%>
<!-- Features Section -->    
<div id="features" class="text-center">
  <div class="container">
    <div class="section-title" data-aos="fade-down">
      <h2>NOS SERVICES</h2>
    </div>
    <div class="row">
      <div class="col-xs-12 col-sm-6" data-aos="fade-up-left">
        <div class="features-item">
          <div class="zoom">
            <h3>Last-Minutes</h3>
            <img src="img/specials/1.jpg" class="img-responsive" alt="">
            <p>Profitez de nos last-minutes pour partir au bord de la mer ou vers une destination plus glaçante !</p>
          </div>
        </div>
      </div>
      <div class="col-xs-12 col-sm-6" data-aos="fade-up-right">
        <div class="features-item">
            <div class="zoom">
              <a href="ListingTraverseesServlet?param=1#Acheter"><h3>Achat de tickets</h3></a>
              <a href="ListingTraverseesServlet?param=1#Acheter"><img href="ListingTraverseesServlet?param=1#Acheter" src="img/specials/2.jpg" class="img-responsive" alt=""></a>
              <p>N'attendez plus et achetez votre billet dans l'une de nos destinations !</p>
            </div>
        </div>
      </div>
    </div>
  </div>
</div>

<jsp:include page="footer.jsp"/>
<script type="text/javascript" src="js/aos.js"></script>
<script type="text/javascript" src="js/jquery.1.11.1.js"></script> 
<script type="text/javascript" src="js/bootstrap.js"></script> 
<script type="text/javascript" src="js/SmoothScroll.js"></script> 
<script type="text/javascript" src="js/jqBootstrapValidation.js"></script> 
<script type="text/javascript" src="js/contact_me.js"></script> 
<script type="text/javascript" src="js/main.js"></script>
<script>
    AOS.init();
</script>
</body>
</html>

