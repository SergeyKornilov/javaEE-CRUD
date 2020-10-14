<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>List of messages</title>
</head>
<body>
    <#list messages as messages>
        <table>
            <tr>
                <td>${messages.author}</td>
                <td>${messages.text}</td>
            </tr>
        </table>
    </#list>
</body>
</html>