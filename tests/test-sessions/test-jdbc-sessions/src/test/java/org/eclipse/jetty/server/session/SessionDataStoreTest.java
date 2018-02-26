//
//  ========================================================================
//  Copyright (c) 1995-2018 Mort Bay Consulting Pty. Ltd.
//  ------------------------------------------------------------------------
//  All rights reserved. This program and the accompanying materials
//  are made available under the terms of the Eclipse Public License v1.0
//  and Apache License v2.0 which accompanies this distribution.
//
//      The Eclipse Public License is available at
//      http://www.eclipse.org/legal/epl-v10.html
//
//      The Apache License v2.0 is available at
//      http://www.opensource.org/licenses/apache2.0.php
//
//  You may elect to redistribute this code under either of these licenses.
//  ========================================================================
//


package org.eclipse.jetty.server.session;

import org.junit.After;
import org.junit.Test;

/**
 * SessionDataStoreTest
 *
 *
 */
public class SessionDataStoreTest extends AbstractSessionDataStoreTest
{
    
    @After
    public void tearDown() throws Exception 
    {
        JdbcTestHelper.shutdown(null);
    }


    /** 
     * @see org.eclipse.jetty.server.session.AbstractSessionDataStoreTest#createSessionDataStoreFactory()
     */
    @Override
    public SessionDataStoreFactory createSessionDataStoreFactory()
    {
        return JdbcTestHelper.newSessionDataStoreFactory();
    }

    /** 
     * @see org.eclipse.jetty.server.session.AbstractSessionDataStoreTest#persistSession(org.eclipse.jetty.server.session.SessionData)
     */
    @Override
    public void persistSession(SessionData data)
    throws Exception
    {
        JdbcTestHelper.insertSession(data.getId(), data.getContextPath(), data.getVhost(), data.getLastNode(), 
                                     data.getCreated(), data.getAccessed(), data.getLastAccessed(), 
                                     data.getMaxInactiveMs(), data.getExpiry(), data.getAllAttributes());

    }




    /** 
     * @see org.eclipse.jetty.server.session.AbstractSessionDataStoreTest#persistUnreadableSession(org.eclipse.jetty.server.session.SessionData)
     */
    @Override
    public void persistUnreadableSession(SessionData data) throws Exception
    {
        JdbcTestHelper.insertSession(data.getId(), data.getContextPath(), data.getVhost(), data.getLastNode(), 
                                     data.getCreated(), data.getAccessed(), data.getLastAccessed(), 
                                     data.getMaxInactiveMs(), data.getExpiry(), null);
        
    }

    
    
    
    
    

    /** 
     * @see org.eclipse.jetty.server.session.AbstractSessionDataStoreTest#checkSessionPersisted(org.eclipse.jetty.server.session.SessionData)
     */
    @Override
    public boolean checkSessionPersisted(SessionData data) throws Exception
    {
        return JdbcTestHelper.existsInSessionTable(data.getId(), false);
    }


    @Test
    public  void testLoadSessionExists() throws Exception
    {
        super.testLoadSessionExists();
    }
    
    
    
    @Test
    public void testLoadSessionExpired() throws Exception
    {
        super.testLoadSessionExpired();
    }
    
    @Test
    public  void testLoadSessionDoesNotExist() throws Exception
    {
        super.testLoadSessionDoesNotExist();
    }
    
    @Test
    public void testLoadSessionFails() throws Exception
    {
        super.testLoadSessionFails();
    }
    
    @Test
    public void testDeleteSessionExists() throws Exception
    {
        super.testDeleteSessionExists();
    }
    
    @Test
    public void testDeleteSessionDoesNotExist() throws Exception
    {
        super.testDeleteSessionDoesNotExist();
    }
}
