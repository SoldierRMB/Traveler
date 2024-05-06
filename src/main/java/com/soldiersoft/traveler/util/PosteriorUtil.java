package com.soldiersoft.traveler.util;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.Variance;

public class PosteriorUtil {

    // 先验分布的参数（正态分布的均值和标准差）
    private static final double PRIOR_MEAN = 5;// 假设初始均值为5
    private static final double PRIOR_STD_DEV = 1.0;// 假设初始标准差为1.0

    public static double calculatePosteriorMean(double[] observations) {
        Result result = getResult(observations);

        // 使用正态分布计算后验分布的参数（均值）
        return (result.priorMean() * result.priorStdDev() * result.priorStdDev() + result.observedMean() * result.observedVariance()) /
                (result.priorStdDev() * result.priorStdDev() + result.observedVariance());
    }

    public static double calculatePosteriorStdDev(double[] observations) {
        Result result = getResult(observations);

        // 使用正态分布计算后验分布的参数（标准差）
        return (Math.sqrt((result.priorStdDev() * result.priorStdDev() * result.observedVariance()) /
                (result.priorStdDev() * result.priorStdDev() + result.observedVariance())));
    }

    private static Result getResult(double[] observations) {

        // 计算观测数据的样本均值和样本方差
        Mean mean = new Mean();
        Variance variance = new Variance();
        double observedMean = mean.evaluate(observations);
        double observedVariance = variance.evaluate(observations);
        return new Result(PRIOR_MEAN, PRIOR_STD_DEV, observedMean, observedVariance);
    }

    private record Result(double priorMean, double priorStdDev, double observedMean, double observedVariance) {
    }
}
