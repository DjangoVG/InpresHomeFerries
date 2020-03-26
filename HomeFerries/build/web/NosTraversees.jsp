<%-- 
    Document   : home
    Created on : 21-oct.-2019, 12:42:48
    Author     : Regis & Yannis
--%>

<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Date"%>
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

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listes des traversées</title>
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
    
    <%
        TraverseesListingBean traversees = (TraverseesListingBean) request.getAttribute("liste_traversees");
        if(request.getAttribute("liste_traversees") != null){
                traversees = (TraverseesListingBean) request.getAttribute("liste_traversees");
        }
        
        %> 
    <div id="features" class="text-center">
       <div class="section-title" data-aos="fade-down" id="NosTraversees">
        <h2>NOS Traversées</h2>
    </div>
        <div class="container-table100" data-aos="fade-up">
                <div class="wrap-table100">
                    <div class="table100 ver3 m-b-110">
                        <div class="table100-head">
                                <table>
                                        <thead>
                                                <tr align="center" class="row100 head">
                                                        <th class="cell100 column1 ">Numéro de traversée</th>
                                                        <th class="cell100 column2">Heure de départ</th>
                                                        <th class="cell100 column3">Port de départ</th>
                                                        <th class="cell100 column4">Port d'arrivée</th>
                                                        <th class="cell100 column5">Matricule du ferry</th>
                                                        <th class="cell100 column6">Prix du ticket</th>
                                                        <th class="cell100 column7">Last Minute</th>
                                                </tr>
                                        </thead>
                                </table>
                        </div>

                        <div class="table100-body js-pscroll">
                                <table>
                                        <tbody>
                                            <%
                                            Iterator <Traversees> list = traversees.getListeTraversees().iterator();
                                            String LastMinute ;
                                            while(list.hasNext()){
                                                    Traversees traversee = list.next();
                                                    System.out.println("Format du DateDepart : " + traversee.getDateDepart().getClass().getTypeName());
                                                    java.util.Date DateDepart = traversee.getDateDepart();
                                                    java.util.Date DateCourant = new java.util.Date();
                                                    System.out.println("Date Depart : " + DateDepart + " DateCourante : " + DateCourant);
                                                    /*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                                                    String DateDepart2 = format.format(DateDepart);
                                                    String DateCourante2 = format.format(DateCourant);
                                                    System.out.println("Date Depart : " + DateDepart2 + " DateCourante : " + DateCourante2);
                                                    java.util.Date datedep = format.parse(DateDepart2);
                                                    java.util.Date datecour = format.parse(DateDepart2);
                                                    System.out.println("Date Depart : " + datedep + " DateCourante : " + datecour);*/
                                                    System.out.println("Date Depart en milliseconde : " + DateDepart.getTime() + " DateCourante : " + DateCourant.getTime());
                                                    if(DateDepart.getTime() < DateCourant.getTime() + 432000000)
                                                    {
                                                        LastMinute = "20%";
                                                        int prix = traversee.getPrix();
                                                        prix = prix - ((prix *20)/100);
                                                        traversee.setPrix(prix);
                                                    }
                                                    else
                                                    {
                                                        LastMinute = "0%";
                                                    }
                                            %>
                                                <tr class="row100 body">
                                                    <td class="cell100 column1"><%=traversee.getIdTraversees()%></td>
                                                    <td class="cell100 column2"><%=traversee.getDateDepart()%></td>
                                                    <td class="cell100 column3"><%=traversee.getPortDepart()%></td>
                                                    <td class="cell100 column4"><%=traversee.getPortArrivee()%></td>
                                                    <td class="cell100 column5"><%=traversee.getMatricule()%></td>
                                                    <td class="cell100 column6"><%=traversee.getPrix()%>€</td>
                                                    <td class="cell100 column7"><%=LastMinute%></td>
                                                </tr>
                                            <%}%>
                                        </tbody>
                                </table>
                        </div>
                </div>
            </div>
    </div>
    </div>
    <jsp:include page="footer.jsp"/>

<script type="text/javascript" src="js/jquery.1.11.1.js"></script> 
<script type="text/javascript" src="js/bootstrap.js"></script> 
<script type="text/javascript" src="js/SmoothScroll.js"></script> 
<script type="text/javascript" src="js/jqBootstrapValidation.js"></script> 
<script type="text/javascript" src="js/contact_me.js"></script> 
<script type="text/javascript" src="js/main.js"></script>
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
