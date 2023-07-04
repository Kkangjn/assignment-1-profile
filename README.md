## 🍃Spring-CRUD 연습
### API 명세서
|Method|URI|Request|Response|
|-----|---|---|---|
|GET|/api/profiles|-|{<br>&nbsp;&nbsp;"id":"id",<br>&nbsp;&nbsp;"title":"title",<br>&nbsp;&nbsp;"name":"name",<br>&nbsp;&nbsp;"contents":"contents",<br>&nbsp;&nbsp;"createdAt":"createdAt",<br>&nbsp;&nbsp;"updatedAt":"updatedAt"<br>},<br>{<br>&nbsp;&nbsp;"id":"id",<br>&nbsp;&nbsp;"title":"title",<br>&nbsp;&nbsp;"name":"name",<br>&nbsp;&nbsp;"contents":"contents",<br>&nbsp;&nbsp;"createdAt":"createdAt",<br>&nbsp;&nbsp;"updatedAt":"updatedAt"<br>}| 
|POST|/api/profile|JSON(profile1(title,name,pw,contents))|JSON(profile1(id,title,name,cotents,createAt))|
|GET|/api/profile/{id}|-|JSON(profile{id}(id,title,name,contents,createAt,updateAt))|
|PUT|/api/profile/{id}|JSON(profile{id}(title,name,contents,pw))|JSON(profile{id}(id,title,name,contents,createAt,updateAt))|
|DELETE|/api/profile/{id}|JSON(profile{id}(pw))|String messager|
---
<table border="1">
	<th>Method</th>
	<th>URI</th>
  <th>Request</th>
  <th>Response</th>
	<tr>
    <td rowspan="2" valign="top">GET</td>
    <td valign="top">/api/profiles</td>
    <td></td>
    <td>{<br>&nbsp;&nbsp;"id":"id",<br>&nbsp;&nbsp;"title":"title",<br>&nbsp;&nbsp;"name":"name",<br>&nbsp;&nbsp;"contents":"contents",<br>&nbsp;&nbsp;"createdAt":"createdAt",<br>&nbsp;&nbsp;"updatedAt":"updatedAt"<br>},<br>{<br>&nbsp;&nbsp;"id":"id",<br>&nbsp;&nbsp;"title":"title",<br>&nbsp;&nbsp;"name":"name",<br>&nbsp;&nbsp;"contents":"contents",<br>&nbsp;&nbsp;"createdAt":"createdAt",<br>&nbsp;&nbsp;"updatedAt":"updatedAt"<br>}</td>
	</tr>
	<tr>
    <td valign="top">/api/profile/{id}</td>
    <td></td>
    <td>{<br>&nbsp;&nbsp;"id":"id",<br>&nbsp;&nbsp;"title":"title",<br>&nbsp;&nbsp;"name":"name",<br>&nbsp;&nbsp;"contents":"contents",<br>&nbsp;&nbsp;"createdAt":"createdAt",<br>&nbsp;&nbsp;"updatedAt":"updatedAt"<br>}</td>
	</tr>
  <tr>
    <td valign="top">POST</td>
    <td valign="top">/api/profile</td>
    <td>{<br>&nbsp;&nbsp;"title":"title",<br>&nbsp;&nbsp;"name":"name",<br>&nbsp;&nbsp;"contents":"contents",<br>&nbsp;&nbsp;"pw":"pw"<br>}</td>
    <td>{<br>&nbsp;&nbsp;"id":"id",<br>&nbsp;&nbsp;"title":"title",<br>&nbsp;&nbsp;"name":"name",<br>&nbsp;&nbsp;"contents":"contents",<br>&nbsp;&nbsp;"createdAt":"createdAt",<br>&nbsp;&nbsp;"updatedAt":"updatedAt"<br>}</td>
  </tr>
</table>  

### JSON column
id, title, name, contents, pw, createAt, updateAt
