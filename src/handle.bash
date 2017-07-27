#!/bin/bash

javac Peer.java PeerClient.java PeerServer.java ReceiveClient.java
java Peer 1 &
java Peer 2 &
java Peer 3 &
java Peer 4 &
