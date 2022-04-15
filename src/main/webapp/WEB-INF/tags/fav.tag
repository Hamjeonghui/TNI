<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
 <%@ attribute name="faid" %>
 <%@ attribute name="aid" %>
 
    <c:if test="${uid != null}">
        <c:choose>
            <c:when test="${faid==1}">
                <a href="fav.do?aid=${aid}&uid=${uid}" style="color: red;">♥</a>
            </c:when>
            <c:otherwise>
               <a id="heart" href="fav.do?aid=${aid}&uid=${uid}" style="color: red;">︎♡︎</a>
            </c:otherwise>
        </c:choose>
    </c:if>