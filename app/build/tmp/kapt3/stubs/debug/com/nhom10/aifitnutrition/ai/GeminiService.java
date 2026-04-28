package com.nhom10.aifitnutrition.ai;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J$\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0003H\u0002J\u0010\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0003H\u0002J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0003H\u0082@\u00a2\u0006\u0002\u0010\u0016J\u0016\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0019H\u0082@\u00a2\u0006\u0002\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J8\u0010\u001e\u001a\u0002H\u001f\"\u0004\b\u0000\u0010\u001f2\"\u0010 \u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001f0\"\u0012\u0006\u0012\u0004\u0018\u00010\u00010!H\u0082@\u00a2\u0006\u0002\u0010#JH\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\u0006\u0010%\u001a\u00020\u00032\u0018\u0010&\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\'0\u00062\b\b\u0002\u0010(\u001a\u00020\u0003H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b)\u0010*R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006+"}, d2 = {"Lcom/nhom10/aifitnutrition/ai/GeminiService;", "", "apiKey", "", "(Ljava/lang/String;)V", "modelCandidates", "", "normalizedApiKey", "analyzeFoodImage", "Lkotlin/Result;", "Lcom/nhom10/aifitnutrition/ai/FoodAnalysisResult;", "bitmap", "Landroid/graphics/Bitmap;", "analyzeFoodImage-gIAlu-s", "(Landroid/graphics/Bitmap;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "buildChatModel", "Lcom/google/ai/client/generativeai/GenerativeModel;", "modelName", "buildVisionModel", "generateChatWithFallback", "Lcom/google/ai/client/generativeai/type/GenerateContentResponse;", "prompt", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateVisionWithFallback", "inputContent", "Lcom/google/ai/client/generativeai/type/Content;", "(Lcom/google/ai/client/generativeai/type/Content;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mapGeminiError", "error", "", "runWithModelFallback", "T", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendChatMessage", "userMessage", "conversationHistory", "Lkotlin/Pair;", "userContext", "sendChatMessage-BWLJW6A", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class GeminiService {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String normalizedApiKey = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> modelCandidates = null;
    
    public GeminiService(@org.jetbrains.annotations.NotNull()
    java.lang.String apiKey) {
        super();
    }
    
    private final com.google.ai.client.generativeai.GenerativeModel buildVisionModel(java.lang.String modelName) {
        return null;
    }
    
    private final com.google.ai.client.generativeai.GenerativeModel buildChatModel(java.lang.String modelName) {
        return null;
    }
    
    private final java.lang.Object generateVisionWithFallback(com.google.ai.client.generativeai.type.Content inputContent, kotlin.coroutines.Continuation<? super com.google.ai.client.generativeai.type.GenerateContentResponse> $completion) {
        return null;
    }
    
    private final java.lang.Object generateChatWithFallback(java.lang.String prompt, kotlin.coroutines.Continuation<? super com.google.ai.client.generativeai.type.GenerateContentResponse> $completion) {
        return null;
    }
    
    private final <T extends java.lang.Object>java.lang.Object runWithModelFallback(kotlin.jvm.functions.Function2<? super java.lang.String, ? super kotlin.coroutines.Continuation<? super T>, ? extends java.lang.Object> block, kotlin.coroutines.Continuation<? super T> $completion) {
        return null;
    }
    
    private final java.lang.String mapGeminiError(java.lang.Throwable error) {
        return null;
    }
}