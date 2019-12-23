package ioUtil;

public class TranscodingUtil {
	public static String gbk2utf8(String gbk) {
		String l_temp = GBK2Unicode(gbk);
		l_temp = unicodeToUtf8(l_temp);
		return l_temp;
	}

	public static String utf82gbk(String utf) throws Exception {
		String l_temp = utf8ToUnicode(utf);
		l_temp = Unicode2GBK(l_temp);
		return l_temp;
	}

	public static String GBK2Unicode(String str) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < str.length(); ++i) {
			char chr1 = str.charAt(i);
			if (!(isNeedConvert(chr1))) {
				result.append(chr1);
			} else {
				result.append("\\u" + Integer.toHexString(chr1));
			}
		}
		return result.toString();
	}

	public static String Unicode2GBK(String dataStr) throws Exception {
		int index = 0;
		StringBuffer buffer = new StringBuffer();

		int li_len = dataStr.length();
		while (true) {
			while (true) {
				if (index >= li_len)
					return buffer.toString();
				if ((index < li_len - 1) && ("\\u".equals(dataStr.substring(index, index + 2))))
					break;
				buffer.append(dataStr.charAt(index));

				++index;
			}

			String charStr = "";
			charStr = dataStr.substring(index + 2, index + 6);

			char letter = (char) Integer.parseInt(charStr, 16);

			buffer.append(letter);
			index += 6;
		}

	}

	public static boolean isNeedConvert(char para) {
		return ((para & 0xFF) != para);
	}

	public static String utf8ToUnicode(String inStr) {
		char[] myBuffer = inStr.toCharArray();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < inStr.length(); ++i) {
			Character.UnicodeBlock ub = Character.UnicodeBlock.of(myBuffer[i]);
			if (ub == Character.UnicodeBlock.BASIC_LATIN) {
				sb.append(myBuffer[i]);
			} else if (ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
				int j = myBuffer[i] - 65248;
				sb.append((char) j);
			} else {
				short s = (short) myBuffer[i];
				String hexS = Integer.toHexString(s);
				String unicode = "\\u" + hexS;
				sb.append(unicode.toLowerCase());
			}
		}
		return sb.toString();
	}

	public static String unicodeToUtf8(String theString) {
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		int x = 0;
		while (true) {
			char aChar;
			while (true) {
				while (true) {
					if (x >= len)
						return outBuffer.toString();
					aChar = theString.charAt(x++);
					if (aChar != '\\') {
						outBuffer.append(aChar);
						return outBuffer.toString();
					}
					aChar = theString.charAt(x++);
					if (aChar != 'u')
						break;
					int value = 0;
					for (int i = 0; i < 4; ++i) {
						aChar = theString.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - 48;
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 97;
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 65;
							break;
						case ':':
						case ';':
						case '<':
						case '=':
						case '>':
						case '?':
						case '@':
						case 'G':
						case 'H':
						case 'I':
						case 'J':
						case 'K':
						case 'L':
						case 'M':
						case 'N':
						case 'O':
						case 'P':
						case 'Q':
						case 'R':
						case 'S':
						case 'T':
						case 'U':
						case 'V':
						case 'W':
						case 'X':
						case 'Y':
						case 'Z':
						case '[':
						case '\\':
						case ']':
						case '^':
						case '_':
						case '\'':
						default:
							throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
						}
					}

					outBuffer.append((char) value);
				}
				if (aChar == 't')
					aChar = '\t';
				else if (aChar == 'r')
					aChar = '\r';
				else if (aChar == 'n')
					aChar = '\n';
				else if (aChar == 'f')
					aChar = '\f';
				outBuffer.append(aChar);
			}

		}
	}
}
