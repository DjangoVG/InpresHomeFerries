<%-- 
    Document   : headerunlogged
    Created on : 06-nov.-2019, 16:03:08
    Author     : Regis & Yannis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home-Ferries</title>
        <link href="css/aos.css" rel="stylesheet">
    </head>
    <body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">
        <nav id="menu" class="navbar navbar-default navbar-fixed-top">
          <div class="container"> 
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
              <ul class="nav navbar-nav">
                <li><a href="home.jsp" class="page-scroll">ACCUEIL</a></li>
                <li><a href="ListingTraverseesServlet?param=0#NosTraversees" class="page-scroll">Nos traversées</a></li>
                <li><a href="ListingTraverseesServlet?param=1#Acheter" class="page-scroll">ACHETER UN TICKET</a></li>
                <li><a href="LoginHome.jsp#FormCheckLogin" class="page-scroll">Se connecter</a></li>
                <li><a href="RegisterHome.jsp#FormCheckRegister" class="page-scroll">S'inscrire</a></li>
              </ul>
            </div>
            <!-- /.navbar-collapse --> 
          </div>
        </nav>
        <!-- Header -->
        <header id="header">
          <div class="intro" data-stellar-background-ratio="0.5">
            <div class="overlay">
              <div class="container">
                <div class="row">
                  <div class="intro-text" data-aos="fade-up">
                    <span class="text-head">Bienvenue,</span>
                    <h1><span class ="type"></span></h1>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-12 text-center">
                <div class="scroll_down">
                    <i class="mbri-arrow-down"></i>
                </div>
            </div>
          </div>
        </header>
        <script src="js/typed.js-master/lib/typed.js"></script>
        <script src="js/stellar.js-master/jquery.stellar.min.js"></script>
        <script type="text/javascript" src="js/aos.js"></script>
        <script>
          var typed = new Typed('.type', {
          strings: ["Home-Ferries", "Naviguez en toute liberté"],
          typeSpeed: 100,
          backSpeed: 30,
          loop : true
        });
        </script>
        <script>
    AOS.init();
</script>
    <script>
    	$.stellar();
    </script>
</html>
