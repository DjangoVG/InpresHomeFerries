<%-- 
    Document   : home
    Created on : 21-oct.-2019, 12:42:48
    Author     : Regis & Yannis
--%>

<%@page import="JavaBean.PanierListingBean"%>
<%@page import="Traversees.Traversees"%>
<%@page import="java.util.Iterator"%>
<%@page import="JavaBean.TraverseesListingBean"%>
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
<link rel="stylesheet" type="text/css"  href="css/style.css">
<link href="https://fonts.googleapis.com/css?family=Raleway:300,400,500,600,700" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Rochester" rel="stylesheet">
<link href="css/aos.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
<link rel="stylesheet" type="text/css" href="vendor/perfect-scrollbar/perfect-scrollbar.css">
<link rel="stylesheet" type="text/css" href="css/util.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" href="sweetalert2.min.css">

</head>
<body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">
    
    <%if (session.getAttribute("NumClient") != null)
    {
        %><jsp:include page="headerlogged.jsp" /><%
    }
    else
    {
        %><jsp:include page="headerunlogged.jsp" /><%
    } %>
    <div class="limiter">
    <div class="container-login100" id="Achat"> 
      <div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
        <form method="post" action=Payment>
            <span class="login100-form-title p-b-32">
                Acheter vos tickets !
              </span>
            <span class="txt1 p-b-11">
                Nom
              </span>
              <div class="wrap-input100 validate-input m-b-36">
                <input class="input100" type="text" name="surname" >
                <span class="focus-input100"></span>
              </div>

              <span class="txt1 p-b-11">
                Prénom
              </span>
              <div class="wrap-input100 validate-input m-b-12">
                <input class="input100" type="text" name="firstname" >
                <span class="focus-input100"></span>
              </div>
            
            <span class="txt1 p-b-11">
                Adresse email
              </span>
              <div class="wrap-input100 validate-input m-b-12">
                <input class="input100" type="text" name="emailclient" >
                <span class="focus-input100"></span>
              </div>

            <span class="txt1 p-b-11">
                Numéro carte de crédit
              </span>
              <div class="wrap-input100 validate-input m-b-12" data-validate = "Numéro de carte de crédit requis">
                <input class="input100" type="text" name="NumCarte" >
                <span class="focus-input100"></span>
              </div>

            <div class="container-login100-form-btn">
                <button type="submit" class="login100-form-btn">
                  Acheter
                </button>
              </div>
        </form>
      </div>
    </div>
<jsp:include page="footer.jsp"/>
<script type="text/javascript" src="js/jquery.1.11.1.js"></script> 
<script type="text/javascript" src="js/bootstrap.js"></script> 
<script type="text/javascript" src="js/SmoothScroll.js"></script> 
<script type="text/javascript" src="js/jqBootstrapValidation.js"></script> 
<script type="text/javascript" src="js/main.js"></script>
<script src="https://js.stripe.com/v3/"></script>
<script src="js/aos.js"></script>
	<script>
		$('.js-pscroll').each(function(){
			var ps = new PerfectScrollbar(this);

			$(window).on('resize', function(){
				ps.update();
			})
		});
	</script>
        <script>
            AOS.init();
        </script>
        
</html>