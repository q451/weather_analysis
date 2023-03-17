import cx_Oracle
conn = cx_Oracle.connect('BI_GX_YN', '1', '172.16.101.105:1521/ORCL')
cursor = conn.cursor()
print('连接数据库成功！')
sql = "select * from BIW_F_BG_DFZHCL"
all = cursor.execute(sql)
print(all.fetchall())
