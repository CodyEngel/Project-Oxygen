# Project Oxygen

Oftentimes I find myself rewriting things in Android projects to help 
with testability. The purpose of this project is to bring those things 
into one common place.

At this time we are not uploading artifacts to JCenter. This is planned
in the very near future though. 

## Project Breakdown

To avoid pulling in unwanted dependencies this project is broken up into 
slices by functionality. Each slice will have two separate dependencies. 
The first is the dependency that should be shipped with your production
binary. The second is the dependency that should be used only for testing
and is the framework that goes along with the actual code.

## By The Slice

Over the lifetime of this project I expect to add additional slices as it
makes sense. This captures each slice and what their intended domain is.

### Core

This focuses on the core of the Android framework. These are the classes
that you couldn't reasonably live without when it comes to working with
Android.

* **StringProvider** decouples the explicit dependency on `Context` and
also provides a simple framework for writing assertion based tests.

### RxJava

TBD - this will be responsible for improving upon RxJava.

### LiveData

TBD - this will be responsible for improving upon LiveData for Android.