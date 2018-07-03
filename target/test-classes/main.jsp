<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<link rel="stylesheet" href="/resources/css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Kodut��d</title>
</head>
<body>
  <h1>Kodut�� tegijad</h1>
  <div class="table-responsive">
        <div class="mobileTD">
            <table class="table table-hover">
                <thead>
                	<tr>
	                    <th th:text="#{person_details.id}">Id</th>
	                    <th th:text="#{person_details.name}">Name</th>
	                    <th th:text="#{person_details.points}">Points</th>
	                    <th th:text="#{person_details.add}">Add homework</th>
	                    <th th:text="#{person_details.reduce}">Add gaming hours</th>
                	</tr>


                </thead>
                <tbody th:each="person : ${persons}" th:remove="tag">
                <tr>
                    <td th:text="${person.getId()}">id</td>
                    <td th:text="${person.getName()}">name</td>
                    <td th:text="${person.getPoints()}">points</td>
                    
                    <td>

                    </td>
                </tr>

                </tbody>
            </table>
        </div>
    </div>
  <hr>
  <br>	 
  <h1>Kodut��d</h1>

</body>
</html>