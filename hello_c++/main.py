import os,sys,popen2,time


#print os.path.exists("Debug/hello_c++.exe")
t = time.time()
r,w = popen2.popen4('D:/git/hello_c++/hello_c++/Debug/hello_c++.exe')

s = """18 = 7 (5 3) 2
30 = 3 3 5
18 = 3 3 5
5 = 3 3
2 = (1) (1)
2 = ((1) 1)
4 = (1) (1) (1) (1)
4 = (1) (1) (1) (1)
999 = (90) (900)(9)
1 = 12 1 1 1 1 1 1 1 1 1 1 1
331776 = 2 3 4 2 3 4 2 3 4 2 3 4
331777 = 2 3 4 2 3 4 2 3 4 2 3 4
10 = 5 20 15
10 = 5 20 5
18 = 7 (5 3) 2
30 = 3 3 5
18 = (((3 3 5)))
30 = (((3 3 5)))
9 = (2 (2 3)) 2
27 = 3 3 ((3))
999 = 900 90 9
999 = 900 90 9
999 = (((((((((((((((((18 5)))))))))((450) (2))(((((((((((((3)))))) 3)))))))))))))))
2 = 1 1
2 = (((((((((((((((((5)))))))))))))))))
5 = (((((((((((((((((5)))))))))))))))))
1 = (1)
123456789 = ((((((((((((((((((((((((((((((123456789))))))))))))))))))))))))))))))
1 = (1 2)
1 = (2 1)
11 = (2 3 4)
11 = ((2 3 4))
8 = ((((((4 5 6)))))) 9
5 = (2 3)
3 = 6 7 6 7
9 = (4 5) 9
3 = 3
4 = 44
19 = 100 200 119
214 = 7 7 7 7 (3 3 3 3 3 (3 3))
999 = 999 999 999
100 = 1 2 3 4 5 6 7 8 9
0"""

s2 = """18 = 7 (5 3) 2
30 = 3 3 5
18 = 3 3 5
5 = 3 3
2=(1)(1)
2=((1)1)
4=(1) (1)(1)(1)
4= (1)(1) (1)(1)
999=(90)(900)(9)
1=12 1 1 1 1 1 1 1 1 1 1 1
331776=2 3 4 2 3 4 2 3 4 2 3 4
331777=2 3 4 2 3 4 2 3 4 2 3 4
10=5 20 15
10=5 20 5
18=7 (5 3) 2
30 = 3 3 5
18 =(((3 3 5)))
30 =(((3 3 5)))
9= (2(2 3))2
27=3 3 ((3))
999=900 90 9
999             =             900                         90                   9
999=(((((((((((((((((18 5)))))))))((450)(2))(((((((((((((3))))))3)))))))))))))))
2=1 1
2       =   (( ((((((((( ((((((5))))))     )))))))))))
5       =   (( ((((((((( ((((((5))))))     )))))))))))
1= (1)
123456789=(((((((((((((((((((((((((((((( 123456789))))))))))))))))))))))))))))))
1= (1 2)
1= (2 1)
11=(2 3 4)
11   =((2 3 4))
8= ((((((4 5  6)) ))   )) 9
5 =(2 3)
3 =6 7 6 7
9 =(4 5) 9
3 =3
4= 44
19=100 200 119
214=7 7 7 7 (3 3 3 3 3 (3 3))
999= 999 999 999
100=1 2 3 4 5 6 7 8 9
0
"""
w.write(s2)
w.close();

xx = r.read()
print time.time()-t
print xx;


