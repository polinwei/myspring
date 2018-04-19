Mongo DB 
=================================


加入登入機制  Add Admin User
----------------------------------
Step 01: start up mongo database 
`mongod.exe --dbpath=D:\MongoDB\db`

Step 02: use data base: admin 
`use admin`

step 03: crate admin user
`db.createUser(
    {
        user: "root",
        pwd: "YOURPASSWORD",
        roles: [{ role: "root", db: "admin" }]
    })`

Add Normal admin & user in Test Database: my_mongo
-----------------------------------------------
**資料庫名稱叫做my_mongo，新增一個 admin 的帳號，有讀取/寫入的權限，以及 jack 的帳號，僅有唯讀的權限**

Step 01: Connect Database: my_mongo
`use my_mongo`

Step 02: 
`db.createUser({
    user: "admin",
    pwd: "PASSWORD",
    roles: [{ role: "readWrite", db: "my_mongo" }]
})`

db.createUser({
    user: "jack",
    pwd: "PASSWORD",
    roles: [{ role: "read", db: "my_mongo" }]
})

**帳號建立好之後，就重新啟動mongod，並加入--auth參數，表示要啟用帳號認證機制**
`mongod --auth --dbpath=YOURDBPATH --logpath=YOURLOGFILEPATH`

Normal start up
----------------
`"C:\Program Files\MongoDB\Server\3.6\bin\mongod.exe" --dbpath=D:\MongoDB\db `

Start up with Authorization
----------------------------
"C:\Program Files\MongoDB\Server\3.6\bin\mongod.exe" --auth --port 27107 --dbpath --dbpath=D:\MongoDB\db
