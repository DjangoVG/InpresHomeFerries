<%-- 
    Document   : login
    Created on : 21-oct.-2019, 12:37:00
    Author     : Regis & Yannis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Home Ferries</title>
<meta name="description" content="">
<meta name="author" content="">

  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->  
  <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
  <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" type="text/css" href="fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
  <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css"> 
  <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
  <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
  <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css"> 
  <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
  <link rel="stylesheet" type="text/css" href="css/util.css">
  <link rel="stylesheet" type="text/css" href="css/main.css">


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

</head>

    <%if (session.getAttribute("NumClient") != null)
    {
        %><jsp:include page="headerlogged.jsp" /><%
    }
    else
    {
        %><jsp:include page="headerunlogged.jsp" /><%
    }%>
    

<!-- Features Section -->
    <div class="limiter">
    <div class="container-login100"> 
      <div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55" id="FormCheckLogin">
        <form method="post" action=LoginCheck class="login100-form validate-form flex-sb flex-w">
            <% 
                if(request.getAttribute("LoginFailed") != null) 
                { %>
                    <div class="w3-panel w3-red">
                        <h2><font color="red">Login incorrect !</font></h2>
                    </div>
                <%} else {%>
                
                <% if(session.getAttribute("NumClient") == null) { %> 
                  <div class="w3-panel w3-red">
                      <h5><font color="red">Vous devez être connecté pour acheter des tickets !</font></h5>
                  </div>
                  <%}}%>

          <span class="login100-form-title p-b-32">
            Se connecter
          </span>

          <span class="txt1 p-b-11">
            Numéro de client
          </span>
          <div class="wrap-input100 validate-input m-b-36" data-validate = "Nom d'utilisateur requis">
            <input class="input100" type="text" name="Username" >
            <span class="focus-input100"></span>
          </div>
          
          <span class="txt1 p-b-11">
            Mot de passe
          </span>
          <div class="wrap-input100 validate-input m-b-12" data-validate = "Mot de passe requis">
            <span class="btn-show-pass">
              <i class="fa fa-eye"></i>
            </span>
            <input class="input100" type="password" name="Password" >
            <span class="focus-input100"></span>
          </div>
          
          <div class="flex-sb-m w-full p-b-48">
            <div class="contact100-form-checkbox">
              <input class="input-checkbox100" id="ckb1" type="checkbox" name="remember-me">
              <label class="label-checkbox100" for="ckb1">
                Remember me
              </label>
            </div>

            <div>
              <a href="#" class="txt3">
                Mot de passe oublié ?
              </a>
            </div>
          </div>

          <div class="container-login100-form-btn">
            <button type="submit" value="login" class="login100-form-btn">
              Se connecter
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
  <div id="dropDownSelect1"></div>
<script type="text/javascript" src="js/jquery.1.11.1.js"></script> 
<script type="text/javascript" src="js/bootstrap.js"></script> 
<script type="text/javascript" src="js/SmoothScroll.js"></script> 
<script type="text/javascript" src="js/jqBootstrapValidation.js"></script> 
<script type="text/javascript" src="js/contact_me.js"></script> 
<script type="text/javascript" src="js/main.js"></script>

  
  
<!--===============================================================================================-->
  <script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
  <script src="vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
  <script src="vendor/bootstrap/js/popper.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
  <script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
  <script src="vendor/daterangepicker/moment.min.js"></script>
  <script src="vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
  <script src="vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
  <script src="js/main.js"></script>
</body>
</html>

