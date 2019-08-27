# Tuesday Exercise - Fundamental Network Topics
[Exercise link - Fundamental Network Topics](https://docs.google.com/document/d/1RbYJ9hyiqSSASZfuWQtsKwFv7ngQP1-4dceRrKy1LzA/edit)

## What is your public IP address right now, and how did you find it?  

Using the website http://ip4.me/ I got the following results:  
School: 5.179.80.204  
Home: 87.61.232.155  
    
## What is your private IP address right now (do this both at home and in school), and who/what gave you that address?  

With the use of the Command Prompt command **ipconfig** I got the following results:  
School: 192.168.56.1 (Ethernet) & 10.50.138.198 (Wi-Fi)   
Home: 192.168.1.83 (Ethernet)

## What’s special about these address ranges?
    
-   10.0.0.0 – 10.255.255.255
-   172.16.0.0 – 172.31.255.255
-   192.168.0.0 – 192.168.255.255
      
Private range IPs meant for use with private networks and *NOT* online.
![image](https://i.imgur.com/1EJgw9X.png)
    
##  What’s special about this ip-address: 127.0.0.1?

Also known as Localhost (thanks to DNS(?)), this IP is a private IP running on the host.   
Localhost bypasses any network interface hardware (NIC) and is run using the Loopback interface. This means that a request goes through the layer, but once it reaches the NIC it is looped back to the host.  
Essentially it will be run locally without access to the remote network.  
We use localhost for (local) testing purposes before implementing a service on the (remote) web.
    
## What kind of service would you expect to find on a server using these ports: 22, 23, 25, 53, 80, 443?  

Port 22 is used for the SSH (Secure Shell) Remote Login Protocol. *(For gaining remote access)*  
Port 23 is used for the Telnet protocol. *("Replaced" by SSH)*  
Port 25 is used for SMTP (Simple Mail Transfer Protocol). *(For handling mail)*  
Port 53 is used for the DNS (Domain Name System) Protocol. *(Name/IP resolution service)*  
Port 80 is used for HTTP (Hypertext Transfer Protocol). *(Handles web requests with the user of a browser application)*  
Port 443 is used for the HTTPS (Secure HTTP)/SSL (Secure Socket Layer) Protocol. 

As with any port, the server in question listens to the specific port to provide its service.
    
## What is the IP address of studypoints.dk and how did you find it?

With the use of the Command Prompt and the command **ping <website\>** I got the following results:  
(Could have used **tracert <website\>** too)  
`Studypoints.dk`:  
```C:\Users\runin>ping studypoints.dk

Pinging studypoints.dk [165.227.137.75] with 32 bytes of data:
Request timed out.
Request timed out.
Request timed out.
Request timed out.

Ping statistics for 165.227.137.75:
Packets: Sent = 4, Received = 0, Lost = 4 (100% loss)
```  
`Studypoints.info`:   
```C:\Users\runin>ping studypoints.info

Pinging studypoints.info [157.230.21.145] with 32 bytes of data:
Reply from 157.230.21.145: bytes=32 time=18ms TTL=52
Reply from 157.230.21.145: bytes=32 time=23ms TTL=52
Reply from 157.230.21.145: bytes=32 time=17ms TTL=52
Reply from 157.230.21.145: bytes=32 time=23ms TTL=52

Ping statistics for 157.230.21.145:
    Packets: Sent = 4, Received = 4, Lost = 0 (0% loss),
Approximate round trip times in milli-seconds:
    Minimum = 17ms, Maximum = 23ms, Average = 20ms
 ```

## If you write https://studypoints.dk in your browser, how did “it” figure out that it should go to the IP address you discovered above?

Thanks to the DNS server, which works to figure out the IP address based on the URL address. ([Details](https://www.cloudflare.com/learning/dns/what-is-dns/))
    
## Explain shortly the purpose of an ip-address and a port-number and why we need both

The IP address is for the "location" of your device on a network.   
Often you will have a private network, where each device will have a private IP address, and these devices will connect to a gateway host. ("Router")  
The gateway will then connect to the public network, using it's public IP address.

Port numbers specify the specific service requested from the host. Ports are used to share network resources efficiently and simultaneously.
    
## What is your (nearest) DNS server,?

I don't know about my nearest, as I manually use Googles public DNS 8.8.8.8/8.8.4.4.  
Can be found using the CMD command **ipconfig /all**.
    
## What is (conceptually) the DNS system and the purpose with a DNS Server?

DNS is often explained as *the phone book of the internet*. IP addresses are used as computers work with binary numbers, something that may not work as well with humans. The DNS handles my request to `facebook.com` by contacting several services to ensure the actual IP Address `31.13.72.36`.
![Image](https://www.cloudflare.com/img/learning/dns/what-is-dns/dns-lookup-diagram.png)
*([Details of step 1-10](https://www.cloudflare.com/learning/dns/what-is-dns/))*
    
## What is your current Gateway, and how did you find it?

I don't know about my *current* gateway, but using **ipconfig /all** my default is 10.50.128.1 (school, wifi) & 192.168.1.1 (home, ethernet).
    
## What is the address of your current DHCP-Server, and how did you find it?

Using **ipconfig /all** it is 10.255.1.10 (school, wifi) 192.168.1.1 (home, ethernet). 

Funnily enough both my DHCP and Gateway are the same (probably due to my really simple router):
![image](https://i.imgur.com/aSpeb2B.png)
    
## Explain (conceptually) about the TCP/IP-protocol stack

The stack consists of four layers (layer names varies) of communication protocols within the scope of networking:  
   1. Application layer  
      Contains protocols regarding data communication services, e.g. HTTP, DNS, FTP, POP, Telnet.  
   2. Transport Layer  
      Contains protocols regarding host-to-host communication, e.g. TCP, UDP.
   3. Internet Layer  
      Contains protocols regarding local networks ("Internetworking"), handles packets and physical addresses.  
   4. Link (Network) Layer  
      Contains protocols regarding communication technologies for the local network. This is the first layer which is also about physical hardware devices. (Ranging from NICs to the physical cable connecting the "link")

([Source](https://docs.google.com/presentation/d/1iXWJoYjDCs568XHDvVwI5I65tnPDRhi--AY-ZwEz9VI/edit#slide=id.p9))

The advantage to the TCP/IP-Protocol is that its Link Layer is interchangeable; the protocol will work with any type of (proper) hardware.
      
IP (Internet Layer) doesn't care if a packet gets to its destination or not, it just sends the packet.  
TCP (Transport Layer) is responsible for routing application protocols to the correct application on the destination computer.  
([Details](https://medium.com/@anna7/internet-protocol-layers-in-internet-protocol-suite-tcp-ip-abe038c0adde))  
    
## Explain about the HTTP Protocol (the following exercises will go much deeper into this protocol)  

HTTP (Hypertext Transfer Protocol) is a protocol handling communications on the World Wide Web.  

An HTTP request is (typically) done on port 80 for specific resources. We all know the requests POST and GET, but there are several more, such as HEAD, PUT, DELETE, TRACE, OPTIONS, CONNECT.

Following a request is a response, which can be as short as a status code (Was the request followed through) or it can contain headers and body content, such as this website itself, with text, images and hyperlinks.

[Interesting read](http://www.steves-internet-guide.com/http-basics/) including a link to the [original 1-page specification](https://www.w3.org/Protocols/HTTP/AsImplemented.html) for HTTP.

Currently HTTP/2 is standardized as of 2015 with an upcoming third version.
    
## Explain (conceptually) how HTTP and TCP/IP are connected (what can HTTP do, and where does it fit into TCP/IP)
HTTP is in the Applications layer and could contain a response with status/headers/body (webpage).  
TCP would be in charge of preparing the request for moving remotely. Port number is assigned.  
IP would be in charge of partitioning the data into packets. IP address is assigned.  
![image](https://miro.medium.com/max/500/1*68SAndvqy3yAdfq9Q83qHg.gif)  
([Details](https://medium.com/@anna7/internet-protocol-layers-in-internet-protocol-suite-tcp-ip-abe038c0adde))
