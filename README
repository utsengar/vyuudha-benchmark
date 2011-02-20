Benchmarks of:  
==============

1. Serialized objects over Socket (IO)  
2. Protocol buffers over Socket (IO)  
3. JGroups (UDP)  


Data:
=====

An object of a class with 1 int and 2 String values.  
1. Serialized size of object: 138bytes  
2. Protocol buffer size: 38bytes  

Results:
========

JGroups (2 nodes): Total execution time with 1000 values: 4463ms  
Socket with protocol buffer: Total execution time with 1000 values: 2274ms  
Socket with serialization: Total execution time with 1000 values: 433ms  

So, plain old sockets for serialization still rocks for small data.  


Notes: It's a basic stop clock benchmarking, I know it's lame, but it works!  
Initial aim was to try out membership, but this can be extended to test data replication too. 

Main project: https://github.com/utkarsh2012/vyuudha  