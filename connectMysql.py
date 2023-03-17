import pymysql

def connectMysql():
    conn = pymysql.connect(host='localhost', user='root', passwd='123456', db='exercise', port=3306, charset='utf8')
    cursor = conn.cursor()
    # 使用 execute() 方法执行 SQL，如果表存在则删除
    cursor.execute("DROP TABLE IF EXISTS weather")
    # 使用预处理语句创建表
    cursor.execute("CREATE TABLE weather "
                   "(city varchar(20) not null , weather varchar(20), wind varchar(20), wind_power varchar(20), max varchar(10), min varchar(10))")
    conn.commit()

    return cursor
