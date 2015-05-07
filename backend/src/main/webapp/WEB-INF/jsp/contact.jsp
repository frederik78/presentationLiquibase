<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<body>
<%--<h1>${personne.prenom} ${personne.nom}, en vacance au : ${personne.pays.nom}</h1>--%>
<h1>${personne.prenom} ${personne.nom}</h1>
<table>
    <thead>
    <tr>
        <th>Provider</th>
        <th>Libell&eacute;</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="adresse" items="${personne.adressesContact}">

        <tr>
            <td>
                <c:out value="${adresse.typeContact.nom}"/></td>
            <td>
                <c:out value="${adresse.libelle}"/></td>
            <td>
                <c:out value="${adresse.libelle}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p/><p/><p/><p/>
<a href="/home">Retour</a>
</body>
</html>