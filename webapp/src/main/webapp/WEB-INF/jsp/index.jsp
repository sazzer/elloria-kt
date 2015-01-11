<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="page.title" /></title>
        <link rel="stylesheet" type="text/css" href="/webjars/bootstrap/3.3.1/css/bootstrap.css" />
        <script type="text/javascript" src="/webjars/requirejs/2.1.15/require.js" data-main="main"></script>
        <script type="text/javascript">
            require.config({
                "paths": {
                    React: "/webjars/react/0.12.2/react",
                    ReactBootstrap: "/webjars/react-bootstrap/0.13.0/react-bootstrap"
                }
            });
        </script>
        <script type="text/javascript" src="/javascript/built/elloria.js"></script>
    </head>
    <body>
    </body>
</html>
