<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<body>
<h1>Liste des contacts</h1>
<table>
    <thead>
    <tr>
        <th>Pr&eacute;mom</th>
        <th>Nom</th>
        <th>Naissance</th>
        <th>Contacts</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="personne" items="${personnes}">
        <tr>
            <td>
                <c:out value="${personne.prenom}"/></td>
            <td>
                <c:out value="${personne.nom}"/></td>
            <td>
                <fmt:formatDate value='${personne.naissance}' pattern="dd-MM-yyyy"/></td>
            <td><a href=<c:url value='/contact/${personne.id}'/>/>Clic</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>