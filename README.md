## 🍃Spring-CRUD 연습
### API 명세서
|Method|URL|Request|Response|
|-----|---|---|---|
|GET|/api/profiles|-|{<br>&nbsp;&nbsp;"id":"id",<br>&nbsp;&nbsp;"title":"title",<br>&nbsp;&nbsp;"name":"name",<br>&nbsp;&nbsp;"contents":"contents",<br>&nbsp;&nbsp;"createdAt":"createdAt",<br>&nbsp;&nbsp;"updatedAt":"updatedAt"<br>},<br>{<br>&nbsp;&nbsp;"id":"id",<br>&nbsp;&nbsp;"title":"title",<br>&nbsp;&nbsp;"name":"name",<br>&nbsp;&nbsp;"contents":"contents",<br>&nbsp;&nbsp;"createdAt":"createdAt",<br>&nbsp;&nbsp;"updatedAt":"updatedAt"<br>}| 
|POST|/api/profile|JSON(profile1(title,name,pw,contents))|JSON(profile1(id,title,name,cotents,createAt))|
|GET|/api/profile/{id}|-|JSON(profile{id}(id,title,name,contents,createAt,updateAt))|
|PUT|/api/profile/{id}|JSON(profile{id}(title,name,contents,pw))|JSON(profile{id}(id,title,name,contents,createAt,updateAt))|
|DELETE|/api/profile/{id}|JSON(profile{id}(pw))|String messager|
---
### JSON column
id, title, name, contents, pw, createAt, updateAt
