package org.reaktivity.specification.nukleus.http_push.specification.internal;

import static java.lang.System.currentTimeMillis;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.kaazing.k3po.lang.el.Function;
import org.kaazing.k3po.lang.el.spi.FunctionMapperSpi;

public final class Functions
{

    private static final MessageDigest MD5;

    static
    {
        try
        {
            MD5 = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static class Mapper extends FunctionMapperSpi.Reflective
    {

        public Mapper()
        {
            super(Functions.class);
        }

        @Override
        public String getPrefixName()
        {
            return "http_push";
        }
    }

    private Functions()
    {
        // utility
    }

    @Function
    public static String date()
    {
        final SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss");
        return format.format(new Date()) + " GMT";
    }

    @Function
    public static String datePlus(int seconds)
    {
        final SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss");
        final Date date = new Date(currentTimeMillis() + (seconds * 1000));
        return format.format(date) + " GMT";
    }

    @Function
    public static String strongEtag()
    {
        return "\"" + MD5.digest(date().getBytes()) + "\"";
    }

    @Function
    public static String weakEtag()
    {
        return "W/" + strongEtag();
    }
}
